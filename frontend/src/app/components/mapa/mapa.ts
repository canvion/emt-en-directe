import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import * as L from 'leaflet';
import { ParadaService } from '../../services/ParadaService';
import { BusService } from '../../services/BusService';
import { FavoritoService } from '../../services/FavoritoService';
import { LineaService } from '../../services/LineaService';
import { Bus } from '../../models/bus.model';
import { Parada } from '../../models/parada.model';

@Component({
  selector: 'app-mapa',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './mapa.html',
  styleUrl: './mapa.css'
})

export class MapaComponent implements OnInit {

  private paradaService = inject(ParadaService);
  private busService = inject(BusService);
  private favoritoService = inject(FavoritoService);
  private lineaService = inject(LineaService);
  private map: any;
  private busMarkers: L.Marker[] = [];

  ngOnInit() {
    this.initMap();
    this.cargarParadas();
    this.iniciarPolling();
  }

  //mapa de palma
  initMap() {

    this.map = L.map('map').setView([39.5696, 2.6502], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      {
        maxZoom: 19, attribution: '©OpenStreetMap'
      }
        ).addTo(this.map);
  }

  //pooling de los buses para la posicion en el mapa cada 10 segundos
  iniciarPolling() {
    this.actualizarBuses();
    setInterval(() => this.actualizarBuses(), 10000);
  }

  cargarParadas() {
    this.paradaService.getAllParadas().subscribe({
      next: (paradas) => {
        const paradesVisibles = paradas.filter(p => p.esParada === true);
        this.mostrarParadas(paradesVisibles);
      },
      error: (err) => console.error('error carregant parades', err)
    });
  }

  mostrarParadas(paradas: Parada[]) {
    paradas.forEach(parada => {

      const marker = L.marker([parada.latitud, parada.longitud], {
        icon: L.divIcon({
          html: '<div style="font-size: 30px;">📍</div>',
          className: 'parada-icon',
          iconSize: [25, 25],  iconAnchor: [12, 12]
        })
      }).addTo(this.map);

      //ventana con informacion de la parada
      marker.bindPopup(`
        <b>${parada.nombre}</b><br>
        Codi: ${parada.codigo}<br>
        <button onclick="window.afegirFavorit(${parada.id})">🤍 añadir a favoritos</button>
      `);
    });

    (window as any).afegirFavorit = (paradaId: number) => {
      this.afegirFavorit(paradaId);
    };
  }

  //marcador para los buses en la posición donde estén
  cargarBus(bus: Bus) {
    const marker = L.marker([bus.latitud, bus.longitud], {
      icon: L.divIcon({
        html: '<div style="font-size: 30px;">🚌</div>',
        className: 'bus-icon',
        iconSize: [30, 30], iconAnchor: [15, 15]
      })
    }).addTo(this.map);


    marker.bindPopup(`
      <b>Bus ${bus.matricula}</b><br>
      Línia: ${bus.lineaNumero} - ${bus.lineaNombre}
    `);

    //la posición se guarda para eliminarlo cuando "se mueva"
    this.busMarkers.push(marker);
  }


  actualizarBuses() {

    this.busMarkers.forEach(marker => marker.remove());
    this.busMarkers = [];

    //añadimos la posición para cada bus en el mapa
    this.lineaService.getAllLineas().subscribe({
      next: (lineas) => {
        lineas.forEach(linea => {
          this.busService.getBusByLinea(linea.id).subscribe({
            next: (bus) => this.cargarBus(bus),
            error: (err) => console.error('error al cargar buses', err)
          });
        });
      }
    });
  }

  //añadimos a favoritos del usuario la para que seleccione
  afegirFavorit(paradaId: number) {
    this.favoritoService.addFavorito(paradaId).subscribe({
      next: () => alert('parada añadida a favoritos!!️'),
      error: () => alert('error al añadirla a favoritos')
    });
  }
}

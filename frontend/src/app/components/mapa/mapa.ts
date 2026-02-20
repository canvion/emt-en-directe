import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import * as L from 'leaflet';
import { ParadaService } from '../../services/ParadaService';
import { BusService } from '../../services/BusService';
import { FavoritoService } from '../../services/FavoritoService';
import { LineaService } from '../../services/LineaService';
import { AuthService } from '../../services/AuthService';
import { Bus } from '../../models/bus.model';
import { Parada } from '../../models/parada.model';
import { Linea } from '../../models/linea.model';


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
  private authService = inject(AuthService);
  private router = inject(Router);
  private favoritosMap: Map<number, number> = new Map();

  //cargamos las paradas, los buses, los favoritos y las lineas y el username
  lineas: Linea[] = [];
  favoritos: any[] = [];

  mostrarLineas = false;
  mostrarFavoritos = false;

  username = localStorage.getItem('username') || 'Usuari';

  ngOnInit() {
    this.initMap();
    this.cargarParadas();
    this.iniciarPolling();
    if (this.estaLogueado) {
      this.cargarFavoritos();
    }
    this.cargarLineas();
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
    (window as any).toggleFavorit = (paradaId: number) => {
      this.toggleFavorito(paradaId);
    };

    paradas.forEach(parada => {

      const marker = L.marker([parada.latitud, parada.longitud], {
        icon: L.divIcon({
          html: '<div style="font-size: 30px;">📍</div>',
          className: 'parada-icon',
          iconSize: [25, 25],  iconAnchor: [12, 12]
        })
      }).addTo(this.map);
      marker.bindPopup(this.crearPopup(parada));
    });
  }

  crearPopup(parada: Parada): string {

    //alternamos el corazon segun si la parada es favorita o no
    const esFavorito = this.favoritosMap.has(parada.id);
    const corazon = esFavorito ? '❤️' : '🤍';
    const texto = esFavorito ? 'eliminar de favorits' : 'afegir a favorits';

    const botonFavorito = this.estaLogueado ? `
      <button onclick="window.toggleFavorit(${parada.id})" class="favorito-btn">
        ${corazon} ${texto}
      </button>` : '';

    return `
      <div class="popup-content">
        <b>${parada.nombre}</b><br>
        Codi: ${parada.codigo}<br>
        ${botonFavorito}
      </div>
    `;
  }

  //recargamos para que cambie el corazon.
  recargarParadas() {
    this.map.eachLayer((layer: any) => {
      if (layer instanceof L.Marker && layer.options.icon?.options?.className === 'parada-icon') {
        this.map.removeLayer(layer);
      }
    });
    this.cargarParadas();
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


    marker.bindPopup(`<b>Bus de la Línia ${bus.lineaNumero}</b><br ${bus.lineaNombre} `);

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

  cargarFavoritos() {
    this.favoritoService.getFavoritos().subscribe({
      next: (favoritos) => {
        this.favoritos = favoritos;
        this.favoritosMap.clear();
        favoritos.forEach((fav: any) => {
          this.favoritosMap.set(fav.paradaId, fav.id);
        });
      },
      error: (err) => console.error('error carregant favorits', err)
    });
  }

  toggleFavorito(paradaId: number) {
    if (this.favoritosMap.has(paradaId)) {
      const favoritoId = this.favoritosMap.get(paradaId)!;
      this.favoritoService.deleteFavorito(favoritoId).subscribe({
        next: () => {
          this.favoritosMap.delete(paradaId);
          this.cargarFavoritos();
          this.recargarParadas();
        }
      });
    } else {
      this.favoritoService.addFavorito(paradaId).subscribe({
        next: (favorito) => {
          this.favoritosMap.set(paradaId, favorito.id);
          this.cargarFavoritos();
          this.recargarParadas();
        }
      });
    }
  }

  cargarLineas() {
    this.lineaService.getAllLineas().subscribe({
      next: (lineas) => {
        this.lineas = lineas;
      },
      error: (err) => console.error('error al cargar las líneas', err)
    });
  }

  eliminarFavorito(id: number) {
    this.favoritoService.deleteFavorito(id).subscribe({
      next: () => {
        this.cargarFavoritos();
        this.recargarParadas();
      },
      error: (err) => console.error('error eliminando el favorito!', err)
    });
  }


  lineaExpandida: number | null = null;
  lineaDetalle: Linea | null = null;

  private lineaPolyline: L.Polyline | null = null;

  //ver paradas al clickar en una línea.
  verDetalleLinea(lineaId: number) {
    if (this.lineaExpandida === lineaId) {
      this.lineaExpandida = null;
      this.lineaDetalle = null;
    } else {
      this.lineaExpandida = lineaId;
      this.lineaService.getLineaById(lineaId).subscribe({
          next: (linea) => {
            // quita la línea anterior si había
            if (this.lineaPolyline) {
              this.lineaPolyline.remove();
              this.lineaPolyline = null;
            }

            // dibuja la línea con TODOS los puntos (incluidos intermedios)
            const coordenadas = linea.paradas
              ?.map(p => [p.latitud, p.longitud] as [number, number]) ?? [];

            this.lineaPolyline = L.polyline(coordenadas, {
              color: linea.color,
              weight: 7,
              opacity: 0.9
            }).addTo(this.map);

            // filtramos para el panel
            linea.paradas = linea.paradas?.filter(p => p.esParada === true);
            this.lineaDetalle = linea;
          },
        error: (err) => console.error('error carregant detall línia', err)
      });
    }
  }

  limpiarLinea() {
    if (this.lineaPolyline) {
      this.lineaPolyline.remove();
      this.lineaPolyline = null;
    }
    this.lineaDetalle = null;
    this.lineaExpandida = null;
    this.mostrarLineas = false;
  }


  //funciones que hacen que se abra un menu u otro.
  toggleLineas() {
    this.mostrarLineas = !this.mostrarLineas;
    this.mostrarFavoritos = false;
  }

  toggleFavoritos() {
    this.mostrarFavoritos = !this.mostrarFavoritos;
    this.mostrarLineas = false;
  }

  tancarTot() {
      this.mostrarLineas = false;
      this.mostrarFavoritos = false;
      this.lineaExpandida = null;
      this.lineaDetalle = null;
    }


  logout() {
    this.authService.logout();
    this.router.navigate(['/mapa']);
    this.recargarParadas()
  }


  get estaLogueado(): boolean {
    return !!localStorage.getItem('token');
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }


}

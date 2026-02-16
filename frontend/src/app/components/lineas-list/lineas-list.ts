import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { LineaService } from '../../services/LineaService';
import { Linea } from '../../models/linea.model';

@Component({
  selector: 'app-lineas-list',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatListModule,
    MatButtonModule
  ],
  templateUrl: './lineas-list.html',
  styleUrl: './lineas-list.css'
})

export class LineasListComponent implements OnInit {

  private lineaService = inject(LineaService);

  lineas: Linea[] = [];
  loading = true;
  errorMessage = '';

  ngOnInit() {
    this.cargarLineas();
  }

  cargarLineas() {
    this.lineaService.getAllLineas().subscribe({
      next: (data) => {
        this.lineas = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('error carregant línies', err);
        this.errorMessage = 'error al carregar línies';
        this.loading = false;
      }
    });
  }
}

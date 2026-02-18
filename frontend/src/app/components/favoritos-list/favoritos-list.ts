import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { FavoritoService } from '../../services/FavoritoService';
import { Favorito } from '../../models/favorito.model';

@Component({
  selector: 'app-favoritos-list',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './favoritos-list.html',
  styleUrl: './favoritos-list.css'
})

export class FavoritosListComponent implements OnInit {

  private favoritoService = inject(FavoritoService);

  favoritos: Favorito[] = [];
  loading = true;
  errorMessage = '';

  ngOnInit() {
    this.cargarFavoritos();
  }

  cargarFavoritos() {
    this.favoritoService.getFavoritos().subscribe({
      next: (data) => {
        this.favoritos = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('error carregant favorits', err);
        this.loading = false;
      }
    });
  }

  eliminarFavorito(id: number) {
    this.favoritoService.deleteFavorito(id).subscribe({
      next: () => {
        this.cargarFavoritos();
      },
      error: (err) => {
        console.error('error eliminant favorit', err);
      }
    });
  }
}

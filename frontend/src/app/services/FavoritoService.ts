import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Favorito, FavoritoRequest } from '../models/favorito.model';

@Injectable({
  providedIn: 'root'
})
export class FavoritoService {
  private baseUrl = `${API_CONFIG.baseUrl}/favoritos`;

  constructor(private http: HttpClient) {}

  getFavoritos(): Observable<Favorito[]> {
    return this.http.get<Favorito[]>(this.baseUrl);
  }

  addFavorito(paradaId: number): Observable<Favorito> {
    const request: FavoritoRequest = { paradaId };
    return this.http.post<Favorito>(this.baseUrl, request);
  }

  deleteFavorito(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}

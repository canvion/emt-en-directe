import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Linea } from '../models/linea.model';

@Injectable({
  providedIn: 'root'
})

export class LineaService {
  private baseUrl = `${API_CONFIG.baseUrl}/lineas`;

  constructor(private http: HttpClient) {}

  getAllLineas(): Observable<Linea[]> {
    return this.http.get<Linea[]>(this.baseUrl);
  }

  getLineaById(id: number): Observable<Linea> {
    return this.http.get<Linea>(`${this.baseUrl}/${id}`);
  }
}

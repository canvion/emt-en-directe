import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Parada } from '../models/parada.model';

@Injectable({
  providedIn: 'root'
})
export class ParadaService {
  private baseUrl = `${API_CONFIG.baseUrl}/paradas`;

  constructor(private http: HttpClient) {}

  getAllParadas(): Observable<Parada[]> {
    return this.http.get<Parada[]>(this.baseUrl);
  }
}

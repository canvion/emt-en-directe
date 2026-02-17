import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { Bus } from '../models/bus.model';

@Injectable({
  providedIn: 'root'
})

export class BusService {
  private baseUrl = `${API_CONFIG.baseUrl}/buses`;

  constructor(private http: HttpClient) {}

  getBusByLinea(lineaId: number): Observable<Bus> {
    return this.http.get<Bus>(`${this.baseUrl}/linea/${lineaId}`);
  }
}

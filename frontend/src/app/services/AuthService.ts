import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { API_CONFIG } from '../config/api.config';
import { LoginRequest, RegisterRequest, JwtResponse, Usuario } from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private baseUrl = `${API_CONFIG.baseUrl}/auth`;
  private usuariosUrl = `${API_CONFIG.baseUrl}/usuarios`;

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<JwtResponse> {
    const credentials: LoginRequest = { email, password };
    return this.http.post<JwtResponse>(`${this.baseUrl}/login`, credentials).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('nombre', response.usuario.nombre);
        localStorage.setItem('email', response.usuario.email);
      })
    );
  }

  register(nombre: string, email: string, password: string): Observable<Usuario> {
    const data: RegisterRequest = { nombre, email, password };
    return this.http.post<Usuario>(`${this.usuariosUrl}/register`, data);
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('nombre');
    localStorage.removeItem('email');
  }

  isAuthenticated(): boolean {
    return localStorage.getItem('token') !== null;
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getCurrentUserName(): string | null {
    return localStorage.getItem('nombre');
  }
}

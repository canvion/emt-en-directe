import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/AuthService';

@Component({
  selector: 'app-mapa',
  standalone: true,
  imports: [],
  templateUrl: './mapa.html',
  styleUrl: './mapa.css'
})
export class MapaComponent {
  private authService = inject(AuthService);
  private router = inject(Router);
}

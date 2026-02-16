import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../services/AuthService';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    RouterModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})

export class NavbarComponent {

  private authService = inject(AuthService);
  private router = inject(Router);

  username = localStorage.getItem('username') || 'Usuari';

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}

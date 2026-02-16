import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AuthService } from '../../services/AuthService';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {
  private authService = inject(AuthService);
  private router = inject(Router);

  credentials = { username: '', password: '' };
  errorMessage = '';

  onSubmit() {
    this.authService.login(this.credentials.username, this.credentials.password).subscribe({
      next: () => {
        this.router.navigate(['/mapa']);
      },
      error: (err) => {
        console.error('erorr de login', err);
        this.errorMessage = 'username o contrasenya incorrectes';
      }
    });
  }

  goToRegister() {
    this.router.navigate(['/register']);
  }
}

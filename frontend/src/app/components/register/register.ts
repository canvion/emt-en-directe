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
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule
  ],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class RegisterComponent {
  private authService = inject(AuthService);
  private router = inject(Router);

  userData = { username: '', email: '', password: '' };
  errorMessage = '';
  successMessage = '';

  onSubmit() {
    this.authService.register(this.userData.username, this.userData.email, this.userData.password).subscribe({
      next: () => {
        this.successMessage = 'regitre correcte!! Redirigint al login...';
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (err) => {
        console.error('error de registre', err);
        this.errorMessage = 'error al registrar-te.';
      }
    });
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }
}

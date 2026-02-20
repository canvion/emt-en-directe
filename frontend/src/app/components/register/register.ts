import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
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
    ReactiveFormsModule,
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
   router = inject(Router);
  private fb = inject(FormBuilder);

  //formulario reactivo para registro
  registerForm: FormGroup = this.fb.group({
    username: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    confirmPassword: ['', [Validators.required]]
  });

  errorMessage = '';
  successMessage = '';

  onSubmit() {
    if (this.registerForm.invalid) {
      return;
    }
    const { username, email, password, confirmPassword } = this.registerForm.value;

    this.authService.register(username, email, password, confirmPassword).subscribe({
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

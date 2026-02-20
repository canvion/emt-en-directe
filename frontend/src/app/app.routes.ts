import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login';
import { RegisterComponent } from './components/register/register';
import { MapaComponent } from './components/mapa/mapa';

export const routes: Routes = [
  { path: '', redirectTo: '/mapa', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'mapa', component: MapaComponent },
  { path: '**', redirectTo: '/mapa' }
];

import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login';
import { RegisterComponent } from './components/register/register';
import { MapaComponent } from './components/mapa/mapa';
import { LineasListComponent } from './components/lineas-list/lineas-list';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'mapa', component: MapaComponent, canActivate: [authGuard] },
  { path: 'lineas', component: LineasListComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: '/login' }
];

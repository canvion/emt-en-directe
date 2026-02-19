import { Component, OnInit } from '@angular/core';
import { RouterOutlet, Router, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})

export class AppComponent implements OnInit {

  mostrarNavbar = false;

  constructor(private router: Router) {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.comprovarNavbar();
    });
  }

  //filtramos url para mostrar el navbar o no.
  comprovarNavbar() {
    const rutaActual = this.router.url;
    const hiHaToken = localStorage.getItem('token') !== null;

    this.mostrarNavbar = hiHaToken && !['/login', '/register'].includes(rutaActual);
  }

  ngOnInit() {
    this.comprovarNavbar();
  }

}

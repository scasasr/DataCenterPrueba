import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  template: `
    <nav class="nav">
      <a routerLink="/recargas" routerLinkActive="active" [routerLinkActiveOptions]="{exact:true}">Registrar recarga</a>
      <a routerLink="/reportes" routerLinkActive="active">Reportes</a>
    </nav>
  `,
  styles: [`
    .nav { display:flex; gap:1rem; padding:1rem; background:#0f172a; }
    .nav a { color:#e2e8f0; text-decoration:none; }
    .active { font-weight:700; text-decoration:underline; }
  `]
})
export class NavbarComponent {}
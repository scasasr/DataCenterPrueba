import { Routes } from '@angular/router';
import { RecargasComponent } from './views/Recargas/recargas/recargas.component';
import { ReportesComponent } from './views/Reportes/reportes/reportes.component';

export const routes: Routes = [
  { path: '', redirectTo: 'recargas', pathMatch: 'full' },
  { path: 'recargas', component: RecargasComponent },
  { path: 'reportes', component: ReportesComponent }, 
  { path: '**', redirectTo: 'recargas' }
];
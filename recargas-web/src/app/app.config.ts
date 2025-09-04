import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { RECARGAS_REPOSITORY } from './shared/ports';
import { ApiRecargasRepository } from './services/api-recargas.repository';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(withFetch()),
    { provide: RECARGAS_REPOSITORY, useClass: ApiRecargasRepository }
  ]
};
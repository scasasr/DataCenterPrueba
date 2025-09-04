import { Injectable, Inject } from '@angular/core';
import { RECARGAS_REPOSITORY, RecargasRepository } from '../shared/ports';
import { RecargaRequest } from '../shared/models';

@Injectable({ providedIn: 'root' })
export class RecargasService {
  constructor(
    @Inject(RECARGAS_REPOSITORY) private repo: RecargasRepository
  ) {}

  getOperadores() { return this.repo.getOperadores(); }
  getVendedores() { return this.repo.getVendedores(); }
  crearRecarga(req: RecargaRequest) { return this.repo.crearRecarga(req); }
  resumenPorOperador(from: string, to: string) { return this.repo.resumenPorOperador(from, to); }
  resumenPorVendedor(from: string, to: string, vendedorId?: number) { return this.repo.resumenPorVendedor(from, to, vendedorId); }
  resumenOperadorVendedor(from: string, to: string) { return this.repo.resumenOperadorVendedor(from, to); }
}

import { InjectionToken } from '@angular/core';
import {
  Operador, Vendedor, RecargaRequest,
  ResumenOperador, ResumenVendedor, ResumenOperadorVendedor
} from './models';


export interface RecargasRepository {
  getOperadores(): Promise<Operador[]>;
  getVendedores(): Promise<Vendedor[]>;
  crearRecarga(req: RecargaRequest): Promise<void>;
  resumenPorOperador(from: string, to: string): Promise<ResumenOperador[]>;
  resumenPorVendedor(from: string, to: string, vendedorId?: number): Promise<ResumenVendedor[]>;
  resumenOperadorVendedor(from: string, to: string): Promise<ResumenOperadorVendedor[]>;
}

export const RECARGAS_REPOSITORY = new InjectionToken<RecargasRepository>('RECARGAS_REPOSITORY');

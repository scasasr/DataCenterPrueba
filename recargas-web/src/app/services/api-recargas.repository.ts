import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { environment } from '../../environments/environment';

import { RecargasRepository } from '../shared/ports';
import {
  Operador, Vendedor, RecargaRequest,
  ResumenOperador, ResumenVendedor, ResumenOperadorVendedor
} from '../shared/models';

@Injectable()
export class ApiRecargasRepository implements RecargasRepository {
  private base = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  getOperadores(): Promise<Operador[]> {
    return firstValueFrom(
      this.http.get<Operador[]>(`${this.base}/operadores`)
    );
  }

  getVendedores(): Promise<Vendedor[]> {
    return firstValueFrom(
      this.http.get<Vendedor[]>(`${this.base}/vendedores`)
    );
  }

  async crearRecarga(req: RecargaRequest): Promise<void> {
    await firstValueFrom(
      this.http.post(`${this.base}/recargas`, req)
    );
  }

  resumenPorOperador(from: string, to: string): Promise<ResumenOperador[]> {
    const params = new HttpParams().set('from', from).set('to', to);
    return firstValueFrom(
      this.http.get<ResumenOperador[]>(`${this.base}/recargas/resumen/operador`, { params })
    );
  }

  resumenPorVendedor(from: string, to: string, vendedorId?: number): Promise<ResumenVendedor[]> {
    let params = new HttpParams().set('from', from).set('to', to);
    if (vendedorId) params = params.set('vendedorId', vendedorId);
    return firstValueFrom(
      this.http.get<ResumenVendedor[]>(`${this.base}/recargas/resumen/vendedor`, { params })
    );
  }

  resumenOperadorVendedor(from: string, to: string): Promise<ResumenOperadorVendedor[]> {
    const params = new HttpParams().set('from', from).set('to', to);
    return firstValueFrom(
      this.http.get<ResumenOperadorVendedor[]>(`${this.base}/recargas/resumen/operador-vendedor`, { params })
    );
  }
}

export interface Operador { id: number; nombre: string; }
export interface Vendedor { id: number; nombre: string; apellido?: string; email?: string; }

export interface RecargaRequest {
  operadorId: number;
  vendedorId: number;
  numeroDestino: string;
  monto: number;
  fechaRecarga?: string; 
}

export interface ResumenOperador { operador: string; cantidad: number; valorTotal: number; }
export interface ResumenVendedor { vendedor: string; cantidad: number; valorTotal: number; }
export interface ResumenOperadorVendedor { operador: string; vendedor: string; cantidad: number; valorTotal: number; }

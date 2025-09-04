import { Component, inject } from '@angular/core';
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RecargasService } from '../../../services/recargas.service';
import { ResumenOperador, ResumenVendedor,ResumenOperadorVendedor, Vendedor } from '../../../shared/models';

@Component({
  selector: 'app-reportes',
  standalone: true,
  imports: [CommonModule, FormsModule, CurrencyPipe, DatePipe],
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.scss']
})
export class ReportesComponent {
  private svc = inject(RecargasService);


  from = this.iso(new Date());
  to   = this.iso(new Date());
  vendedorId?: number;


  vendedores: Vendedor[] = [];


  porOperador: ResumenOperador[] = [];
  porVendedor: ResumenVendedor[] = [];


  loadingOp = false;
  loadingVen = false;
  errOp = '';
  errVen = '';

  async ngOnInit() {
    this.vendedores = await this.svc.getVendedores();
  }

  iso(d: Date) {
    return new Date(Date.UTC(d.getFullYear(), d.getMonth(), d.getDate()))
      .toISOString().slice(0, 10); 
  }


  async cargarOperador() {
    this.errOp = ''; this.loadingOp = true;
    try {
      this.porOperador = await this.svc.resumenPorOperador(this.from, this.to);
    } catch (e: any) {
      this.errOp = e?.message || 'Error consultando resumen por operador';
      this.porOperador = [];
    } finally {
      this.loadingOp = false;
    }
  }

  async cargarVendedor() {
    this.errVen = ''; this.loadingVen = true;
    try {
      this.porVendedor = await this.svc.resumenPorVendedor(this.from, this.to, this.vendedorId);
    } catch (e: any) {
      this.errVen = e?.message || 'Error consultando resumen por vendedor';
      this.porVendedor = [];
    } finally {
      this.loadingVen = false;
    }
  }

  totalCantidadOp()  { return this.porOperador.reduce((a, x) => a + x.cantidad, 0); }
  totalValorOp()     { return this.porOperador.reduce((a, x) => a + x.valorTotal, 0); }
  totalCantidadVen() { return this.porVendedor.reduce((a, x) => a + x.cantidad, 0); }
  totalValorVen()    { return this.porVendedor.reduce((a, x) => a + x.valorTotal, 0); }

  exportCsvOperador() {
    this.exportCsv(
      ['Operador','Cantidad','ValorTotal'],
      this.porOperador.map(r => [r.operador, r.cantidad, r.valorTotal]),
      `resumen-operador_${this.from}_a_${this.to}.csv`
    );
  }
  exportCsvVendedor() {
    this.exportCsv(
      ['Vendedor','Cantidad','ValorTotal'],
      this.porVendedor.map(r => [r.vendedor, r.cantidad, r.valorTotal]),
      `resumen-vendedor_${this.from}_a_${this.to}.csv`
    );
  }
  private exportCsv(headers: string[], rows: (string|number)[][], filename: string) {
    const csv = [headers.join(','), ...rows.map(r => r.join(','))].join('\n');
    const blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a'); a.href = url; a.download = filename; a.click();
    URL.revokeObjectURL(url);
  }
}

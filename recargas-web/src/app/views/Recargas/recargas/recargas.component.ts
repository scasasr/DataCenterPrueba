import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RecargasService } from '../../../services/recargas.service';
import { Operador,Vendedor } from '../../../shared/models';

@Component({
  selector: 'app-recargas',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './recargas.component.html',
  styleUrls: ['./recargas.component.scss']
})
export class RecargasComponent implements OnInit {
  private fb = inject(FormBuilder);
  private svc = inject(RecargasService);

  operadores: Operador[] = [];
  vendedores: Vendedor[] = [];

  msg = '';
  err = '';

  form = this.fb.group({
    operadorId: [null as number | null, Validators.required],
    vendedorId: [null as number | null, Validators.required],
    numeroDestino: ['', [Validators.required, Validators.pattern(/^3\d{9}$/)]],
    monto: [null as number | null, [Validators.required, Validators.min(1000)]]
  });

  async ngOnInit() {
    this.operadores = await this.svc.getOperadores();
    this.vendedores = await this.svc.getVendedores();
  }

  async registrar() {
  this.msg = this.err = '';
  if (this.form.invalid) {
    this.err = 'Revisa los campos del formulario.';
    this.form.markAllAsTouched();
    return;
  }
  const v = this.form.value;
  const hoy = new Date().toISOString().slice(0,10); 
  try {
    await this.svc.crearRecarga({
      operadorId: v.operadorId!,
      vendedorId: v.vendedorId!,
      numeroDestino: v.numeroDestino!,
      monto: v.monto!,
      fechaRecarga: hoy
    });
    this.msg = 'Recarga registrada';
    this.form.reset();
    setTimeout(() => this.msg = '', 2000);
  } catch (e: any) {
    this.err = e?.message || 'Error registrando la recarga';
    setTimeout(() => this.err = '', 4000);
  }
}
}

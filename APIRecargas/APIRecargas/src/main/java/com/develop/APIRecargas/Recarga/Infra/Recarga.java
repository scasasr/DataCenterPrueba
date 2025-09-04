package com.develop.APIRecargas.Recarga.Infra;

import java.time.LocalDate;

import com.develop.APIRecargas.Operador.Infra.Operador;
import com.develop.APIRecargas.Vendedor.Infra.Vendedor;



import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Recarga {
    @Id
    @GeneratedValue
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "operador_id", nullable = false)
    private Operador operador;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @Basic
    private String numeroDestino;
    private int monto;
    private LocalDate fechaRecarga;
    private String estado;
}

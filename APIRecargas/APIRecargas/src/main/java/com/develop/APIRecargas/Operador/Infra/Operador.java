package com.develop.APIRecargas.Operador.Infra;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Operador {
    @Id
    @GeneratedValue
    private Integer Id;
    @Basic
    private String nombre;
}

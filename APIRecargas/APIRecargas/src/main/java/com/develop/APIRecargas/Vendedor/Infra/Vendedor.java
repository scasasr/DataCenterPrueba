package com.develop.APIRecargas.Vendedor.Infra;

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
public class Vendedor {
    @Id
    @GeneratedValue
    private Integer id;
    @Basic
    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private String email;
}

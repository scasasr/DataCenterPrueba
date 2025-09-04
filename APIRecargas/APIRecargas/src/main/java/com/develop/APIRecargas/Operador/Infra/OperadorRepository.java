package com.develop.APIRecargas.Operador.Infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperadorRepository extends JpaRepository <Operador, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);

}

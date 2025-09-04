package com.develop.APIRecargas.Vendedor.Infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.*;

@Repository
public interface VendedorRepository extends JpaRepository <Vendedor,Integer> {
    
    Page<Vendedor> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(
    String nombre, String apellido, Pageable pageable);
}

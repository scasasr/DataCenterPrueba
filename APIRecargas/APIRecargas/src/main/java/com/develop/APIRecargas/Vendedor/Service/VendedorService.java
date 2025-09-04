
package com.develop.APIRecargas.Vendedor.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.develop.APIRecargas.Vendedor.Infra.Vendedor;
import com.develop.APIRecargas.Vendedor.Infra.VendedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VendedorService {
  private final VendedorRepository repo;

  @Transactional(readOnly = true)
  public List<Vendedor> listar() { return repo.findAll(); }

  @Transactional(readOnly = true)
  public Vendedor obtener(Integer id) {
    return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Vendedor no encontrado"));
  }

  public Vendedor crear(Vendedor v) {
    if (v.getNombre()==null || v.getNombre().isBlank()) throw new IllegalArgumentException("Nombre obligatorio");
    return repo.save(v);
  }

  public Vendedor actualizar(Integer id, Vendedor v) {
    Vendedor actual = obtener(id);
    actual.setNombre(v.getNombre());
    actual.setApellido(v.getApellido());
    actual.setNumeroDocumento(v.getNumeroDocumento());
    actual.setEmail(v.getEmail());
    return repo.save(actual);
  }

  public void eliminar(Integer id) {
    Vendedor actual = obtener(id);
    repo.delete(actual);
  }
}

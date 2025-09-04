
package com.develop.APIRecargas.Vendedor.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.develop.APIRecargas.Vendedor.Infra.Vendedor;
import com.develop.APIRecargas.Vendedor.Service.VendedorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vendedores")
@RequiredArgsConstructor
public class VendedorController {

  private final VendedorService service;

  @GetMapping
  public List<Vendedor> listar() {
    return service.listar();
  }

  @GetMapping("/{id}")
  public Vendedor obtener(@PathVariable Integer id) {
    return service.obtener(id);
  }

  @PostMapping
  public ResponseEntity<Vendedor> crear(@RequestBody Vendedor v) {
    return ResponseEntity.ok(service.crear(v));
  }

  @PutMapping("/{id}")
  public Vendedor actualizar(@PathVariable Integer id, @RequestBody Vendedor v) {
    return service.actualizar(id, v);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
    service.eliminar(id);
    return ResponseEntity.noContent().build();
  }
}

// src/main/java/com/develop/APIRecargas/Operador/Controller/OperadorController.java
package com.develop.APIRecargas.Operador.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.develop.APIRecargas.Operador.Infra.Operador;
import com.develop.APIRecargas.Operador.Service.OperadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/operadores")
@RequiredArgsConstructor
public class OperadorController {

    private final OperadorService service;

    @GetMapping
    public List<Operador> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Operador obtener(@PathVariable Integer id) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<Operador> crear(@RequestBody Operador o) {
        return ResponseEntity.ok(service.crear(o));
    }

    @PutMapping("/{id}")
    public Operador actualizar(@PathVariable Integer id, @RequestBody Operador o) {
        return service.actualizar(id, o);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

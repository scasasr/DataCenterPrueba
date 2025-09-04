package com.develop.APIRecargas.Operador.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.develop.APIRecargas.Operador.Infra.Operador;
import com.develop.APIRecargas.Operador.Infra.OperadorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OperadorService {

    private final OperadorRepository repo;

    @Transactional(readOnly = true)
    public List<Operador> listar() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Operador obtener(Integer id) {
        return repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Operador no encontrado"));
    }

    public Operador crear(Operador o) {
        if (o.getNombre() == null || o.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (repo.existsByNombreIgnoreCase(o.getNombre())) {
            throw new IllegalStateException("Ya existe un operador con ese nombre");
        }
        return repo.save(o);
    }

    public Operador actualizar(Integer id, Operador o) {
        Operador actual = obtener(id);
        if (o.getNombre() == null || o.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        // si cambió el nombre, valida unicidad
        if (!actual.getNombre().equalsIgnoreCase(o.getNombre())
            && repo.existsByNombreIgnoreCase(o.getNombre())) {
            throw new IllegalStateException("Ya existe un operador con ese nombre");
        }
        actual.setNombre(o.getNombre());
        return repo.save(actual);
    }

    public void eliminar(Integer id) {
        Operador actual = obtener(id);
        repo.delete(actual);
    }
}

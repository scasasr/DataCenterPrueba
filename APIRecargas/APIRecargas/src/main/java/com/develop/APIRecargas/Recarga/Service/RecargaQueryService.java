package com.develop.APIRecargas.Recarga.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.develop.APIRecargas.Recarga.Infra.RecargaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RecargaQueryService {
  private final RecargaRepository repo;

  public List<Object[]> porOperador(LocalDate from, LocalDate to) {
    return repo.resumenPorOperador(from, to);
  }

  public List<Object[]> porVendedor(Integer vendedorId, LocalDate from, LocalDate to) {
    // vendedorId puede venir null: el query lo maneja con "(:vendedorId is null or ...)"
    return repo.resumenPorVendedor(vendedorId, from, to);
  }

  public List<Object[]> porOperadorVendedor(LocalDate from, LocalDate to) {
    return repo.resumenOperadorVendedor(from, to);
  }
}

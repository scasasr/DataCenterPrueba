package com.develop.APIRecargas.Recarga.domain.DTO;

public record ResumenOperadorVendedorDTO(
  String operador,
  String vendedor,
  long   cantidad,
  long   valorTotal
) {}
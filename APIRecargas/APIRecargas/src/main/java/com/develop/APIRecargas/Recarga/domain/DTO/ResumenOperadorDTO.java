package com.develop.APIRecargas.Recarga.domain.DTO;

public record ResumenOperadorDTO(
  String operador,
  long   cantidad,
  long   valorTotal
) {}

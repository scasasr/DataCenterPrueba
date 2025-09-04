package com.develop.APIRecargas.Recarga.domain.DTO;

public record ResumenVendedorDTO(
  String vendedor,
  long   cantidad,
  long   valorTotal
) {}
package com.develop.APIRecargas.Recarga.domain.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public record RecargaRequest(
  @NotNull Integer operadorId,
  @NotNull Integer vendedorId,
  @NotBlank @Size(max=20) String numeroDestino,
  @Positive int monto,
  LocalDate fechaRecarga   
) {}
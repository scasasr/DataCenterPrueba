package com.develop.APIRecargas.Recarga.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develop.APIRecargas.Recarga.Infra.Recarga;
import com.develop.APIRecargas.Recarga.Service.RecargaQueryService;
import com.develop.APIRecargas.Recarga.Service.RecargaService;
import com.develop.APIRecargas.Recarga.domain.DTO.RecargaRequest;
import com.develop.APIRecargas.Recarga.domain.DTO.ResumenOperadorDTO;
import com.develop.APIRecargas.Recarga.domain.DTO.ResumenOperadorVendedorDTO;
import com.develop.APIRecargas.Recarga.domain.DTO.ResumenVendedorDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recargas")
@RequiredArgsConstructor
public class RecargaController {

    private final RecargaService recargaService;
    private final RecargaQueryService queryService;

  
  @PostMapping
  public ResponseEntity<Recarga> crear(@Validated @RequestBody RecargaRequest req){
    Recarga r = recargaService.CreateRecarga(
      req.operadorId(), req.vendedorId(),
      req.numeroDestino(), req.monto(), req.fechaRecarga());
    return ResponseEntity.ok(r);
  }


  @GetMapping("/resumen/operador")
  public List<ResumenOperadorDTO> resumenOperador(
      @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate from,
    @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate to){
    return queryService.porOperador(from, to).stream()
      .map(x -> new ResumenOperadorDTO(
          (String)x[0],
          ((Number)x[1]).longValue(),
          ((Number)x[2]).longValue()))
      .toList();
  }

 
  @GetMapping("/resumen/vendedor")
  public List<ResumenVendedorDTO> resumenVendedor(
      @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate from,
    @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate to,
      @RequestParam(required=false) Integer vendedorId){
    return queryService.porVendedor(vendedorId, from, to).stream()
      .map(x -> new ResumenVendedorDTO(
          (String)x[0],
          ((Number)x[1]).longValue(),
          ((Number)x[2]).longValue()))
      .toList();
  }

  @GetMapping("/resumen/operador-vendedor")
  public List<ResumenOperadorVendedorDTO> resumenOperadorVendedor(
      @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate from,
    @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate to){
    return queryService.porOperadorVendedor(from, to).stream()
      .map(x -> new ResumenOperadorVendedorDTO(
          (String)x[0],
          (String)x[1],
          ((Number)x[2]).longValue(),
          ((Number)x[3]).longValue()))
      .toList();
  }

}

package com.develop.APIRecargas.Recarga.Infra;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecargaRepository extends JpaRepository<Recarga,Integer>{


  @Query("""
    select 
      r.operador.nombre as operador,
      count(r)          as cantidad,
      sum(r.monto)      as valorTotal
    from Recarga r
    where r.fechaRecarga between :from and :to
    group by r.operador.id, r.operador.nombre
    order by r.operador.nombre
  """)
  List<Object[]> resumenPorOperador(
      @Param("from") LocalDate from,
      @Param("to") LocalDate to);

  @Query("""
    select 
      concat(v.nombre, ' ', v.apellido) as vendedor,
      count(r)                           as cantidad,
      sum(r.monto)                       as valorTotal
    from Recarga r
    join r.vendedor v
    where r.fechaRecarga between :from and :to
      and ( :vendedorId is null or v.id = :vendedorId )
    group by v.id, v.nombre, v.apellido
    order by v.nombre, v.apellido
  """)
  List<Object[]> resumenPorVendedor(
      @Param("vendedorId") Integer vendedorId,
      @Param("from") LocalDate from,
      @Param("to") LocalDate to);


  @Query("""
    select 
      r.operador.nombre               as operador,
      concat(v.nombre,' ',v.apellido) as vendedor,
      count(r)                        as cantidad,
      sum(r.monto)                    as valorTotal
    from Recarga r
    join r.vendedor v
    where r.fechaRecarga between :from and :to
    group by r.operador.id, r.operador.nombre, v.id, v.nombre, v.apellido
    order by r.operador.nombre, v.nombre, v.apellido
  """)
  List<Object[]> resumenOperadorVendedor(
      @Param("from") LocalDate from,
      @Param("to") LocalDate to);
}
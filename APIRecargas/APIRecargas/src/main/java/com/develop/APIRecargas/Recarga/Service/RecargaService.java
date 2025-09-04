package com.develop.APIRecargas.Recarga.Service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.develop.APIRecargas.Operador.Infra.Operador;
import com.develop.APIRecargas.Operador.Infra.OperadorRepository;
import com.develop.APIRecargas.Recarga.Infra.Recarga;
import com.develop.APIRecargas.Recarga.Infra.RecargaRepository;
import com.develop.APIRecargas.Vendedor.Infra.Vendedor;
import com.develop.APIRecargas.Vendedor.Infra.VendedorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecargaService {

    private final RecargaRepository recargaRepository;
    private final OperadorRepository operadorRepository;
    private final VendedorRepository vendedorRepository;

    @Transactional
    public Recarga CreateRecarga(Integer operadorId, Integer vendedorId, String numeroDestino, int monto, LocalDate fecha){
        Operador op = operadorRepository.findById(operadorId)
            .orElseThrow(() -> new IllegalArgumentException("Operador no existe"));
        Vendedor ve = vendedorRepository.findById(vendedorId)
            .orElseThrow(() -> new IllegalArgumentException("Vendedor no existe"));

        Recarga r = new Recarga();
        r.setOperador(op);
        r.setVendedor(ve);
        r.setNumeroDestino(numeroDestino);
        r.setMonto(monto);
        r.setFechaRecarga(fecha != null ? fecha : LocalDate.now());
        r.setEstado("CONFIRMADA");
        return recargaRepository.save(r);
    }
}

package com.tecnogestion.service;

import com.tecnogestion.model.Venta;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Almacenamiento en memoria de ventas (sin base de datos).
 */
@Service
public class VentaService {

    private final List<Venta> ventas = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(0);

    public List<Venta> listar() {
        return ventas;
    }

    public void agregar(Venta venta) {
        venta.setId(contador.incrementAndGet());
        venta.setFecha(LocalDate.now());
        ventas.add(venta);
    }

    public void eliminar(Long id) {
        ventas.removeIf(v -> v.getId().equals(id));
    }
}

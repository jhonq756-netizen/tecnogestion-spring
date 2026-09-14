package com.tecnogestion.service;

import com.tecnogestion.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Almacenamiento en memoria de productos (sin base de datos).
 */
@Service
public class ProductoService {

    private final List<Producto> productos = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(0);

    public ProductoService() {
        agregar(new Producto(null, "Laptop Core i5 16GB", "Equipos", 2899.90, 12));
        agregar(new Producto(null, "Mouse inalambrico", "Accesorios", 39.90, 50));
        agregar(new Producto(null, "Impresora multifuncional", "Equipos", 649.00, 8));
    }

    public List<Producto> listar() {
        return productos;
    }

    public void agregar(Producto producto) {
        producto.setId(contador.incrementAndGet());
        productos.add(producto);
    }

    public void eliminar(Long id) {
        productos.removeIf(p -> p.getId().equals(id));
    }
}

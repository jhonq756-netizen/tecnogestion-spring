package com.tecnogestion.service;

import com.tecnogestion.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Almacenamiento en memoria de clientes (sin base de datos).
 */
@Service
public class ClienteService {

    private final List<Cliente> clientes = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(0);

    public ClienteService() {
        agregar(new Cliente(null, "Comercial Los Andes S.R.L.", "20458796321", "987654321", "contacto@losandes.com"));
        agregar(new Cliente(null, "Inversiones del Norte E.I.R.L.", "20601122334", "956412378", "ventas@invnorte.com"));
    }

    public List<Cliente> listar() {
        return clientes;
    }

    public void agregar(Cliente cliente) {
        cliente.setId(contador.incrementAndGet());
        clientes.add(cliente);
    }

    public void eliminar(Long id) {
        clientes.removeIf(c -> c.getId().equals(id));
    }
}

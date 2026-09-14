package com.tecnogestion.service;

import com.tecnogestion.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de usuarios en memoria (sin base de datos).
 * Usuario de prueba: admin / admin123
 */
@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        usuarios.add(new Usuario("admin", "admin123", "Administrador TecnoGestion"));
    }

    public boolean validar(String usuario, String password) {
        return usuarios.stream()
                .anyMatch(u -> u.getUsuario().equals(usuario) && u.getPassword().equals(password));
    }

    public Usuario buscarPorUsuario(String usuario) {
        return usuarios.stream()
                .filter(u -> u.getUsuario().equals(usuario))
                .findFirst()
                .orElse(null);
    }
}

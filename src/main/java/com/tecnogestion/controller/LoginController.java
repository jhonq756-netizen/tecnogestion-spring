package com.tecnogestion.controller;

import com.tecnogestion.model.Usuario;
import com.tecnogestion.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String raiz() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin(HttpSession session) {
        if (session.getAttribute("usuarioActivo") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String usuario,
                                 @RequestParam String password,
                                 Model model,
                                 HttpSession session) {
        if (usuarioService.validar(usuario, password)) {
            Usuario u = usuarioService.buscarPorUsuario(usuario);
            session.setAttribute("usuarioActivo", u.getUsuario());
            session.setAttribute("nombreUsuario", u.getNombreCompleto());
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Usuario o contrasena incorrectos.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

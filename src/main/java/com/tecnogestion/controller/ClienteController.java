package com.tecnogestion.controller;

import com.tecnogestion.model.Cliente;
import com.tecnogestion.service.ClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {
        if (session.getAttribute("usuarioActivo") == null) {
            return "redirect:/login";
        }
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("nuevoCliente", new Cliente());
        return "clientes";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute Cliente nuevoCliente, HttpSession session) {
        if (session.getAttribute("usuarioActivo") == null) {
            return "redirect:/login";
        }
        clienteService.agregar(nuevoCliente);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuarioActivo") == null) {
            return "redirect:/login";
        }
        clienteService.eliminar(id);
        return "redirect:/clientes";
    }
}

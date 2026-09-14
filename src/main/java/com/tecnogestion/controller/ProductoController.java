package com.tecnogestion.controller;

import com.tecnogestion.model.Producto;
import com.tecnogestion.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {
        if (session.getAttribute("usuarioActivo") == null) {
            return "redirect:/login";
        }
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("nuevoProducto", new Producto());
        return "productos";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute Producto nuevoProducto, HttpSession session) {
        if (session.getAttribute("usuarioActivo") == null) {
            return "redirect:/login";
        }
        productoService.agregar(nuevoProducto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuarioActivo") == null) {
            return "redirect:/login";
        }
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}

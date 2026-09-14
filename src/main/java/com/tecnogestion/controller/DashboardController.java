package com.tecnogestion.controller;

import com.tecnogestion.service.ClienteService;
import com.tecnogestion.service.ProductoService;
import com.tecnogestion.service.VentaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final VentaService ventaService;

    public DashboardController(ClienteService clienteService,
                                ProductoService productoService,
                                VentaService ventaService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.ventaService = ventaService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("usuarioActivo") == null) {
            return "redirect:/login";
        }
        model.addAttribute("nombreUsuario", session.getAttribute("nombreUsuario"));
        model.addAttribute("totalClientes", clienteService.listar().size());
        model.addAttribute("totalProductos", productoService.listar().size());
        model.addAttribute("totalVentas", ventaService.listar().size());
        double totalIngresos = ventaService.listar().stream().mapToDouble(v -> v.getTotal()).sum();
        model.addAttribute("totalIngresos", totalIngresos);
        return "dashboard";
    }
}

package com.tecnogestion.controller;

import com.tecnogestion.model.Venta;
import com.tecnogestion.service.ClienteService;
import com.tecnogestion.service.ProductoService;
import com.tecnogestion.service.VentaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final ClienteService clienteService;
    private final ProductoService productoService;

    public VentaController(VentaService ventaService,
                            ClienteService clienteService,
                            ProductoService productoService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {
        if (session.getAttribute("usuarioActivo") == null) {
            return "redirect:/login";
        }
        model.addAttribute("ventas", ventaService.listar());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("nuevaVenta", new Venta());
        return "ventas";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute Venta nuevaVenta, HttpSession session) {
        if (session.getAttribute("usuarioActivo") == null) {
            return "redirect:/login";
        }
        double precioUnitario = productoService.listar().stream()
                .filter(p -> p.getNombre().equals(nuevaVenta.getProducto()))
                .findFirst()
                .map(p -> p.getPrecio())
                .orElse(0.0);
        nuevaVenta.setTotal(precioUnitario * nuevaVenta.getCantidad());
        ventaService.agregar(nuevaVenta);
        return "redirect:/ventas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuarioActivo") == null) {
            return "redirect:/login";
        }
        ventaService.eliminar(id);
        return "redirect:/ventas";
    }
}

package com.tecnogestion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class TecnogestionApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // Simula a un usuario que ya inició sesión.
    private MockHttpSession sesionIniciada() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("usuarioActivo", "admin");
        session.setAttribute("nombreUsuario", "Administrador TecnoGestion");
        return session;
    }

    // ---------------------- Funcionamiento general ----------------------

    @Test
    void contextLoads() {
        // Verifica que la aplicación Spring Boot arranca correctamente.
    }

    // ---------------------- Login y seguridad ----------------------

    @Test
    void loginMuestraLaPantallaDeInicio() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void loginCorrectoRedirigeAlDashboard() throws Exception {
        mockMvc.perform(post("/login")
                        .param("usuario", "admin")
                        .param("password", "admin123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }

    @Test
    void loginIncorrectoVuelveAlLogin() throws Exception {
        mockMvc.perform(post("/login")
                        .param("usuario", "admin")
                        .param("password", "incorrecta"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void ventasSinSesionRedirigeAlLogin() throws Exception {
        mockMvc.perform(get("/ventas"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    // ---------------------- Interfaces (frontend) ----------------------
    // Cada prueba abre una pantalla con sesión iniciada. Si la plantilla
    // Thymeleaf tiene un error, la pantalla no se genera y la prueba falla.

    @Test
    void dashboardSeGeneraCorrectamente() throws Exception {
        mockMvc.perform(get("/dashboard").session(sesionIniciada()))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
    }

    @Test
    void clientesSeGeneraCorrectamente() throws Exception {
        mockMvc.perform(get("/clientes").session(sesionIniciada()))
                .andExpect(status().isOk())
                .andExpect(view().name("clientes"));
    }

    @Test
    void productosSeGeneraCorrectamente() throws Exception {
        mockMvc.perform(get("/productos").session(sesionIniciada()))
                .andExpect(status().isOk())
                .andExpect(view().name("productos"));
    }

    @Test
    void ventasSeGeneraCorrectamente() throws Exception {
        mockMvc.perform(get("/ventas").session(sesionIniciada()))
                .andExpect(status().isOk())
                .andExpect(view().name("ventas"));
    }
}

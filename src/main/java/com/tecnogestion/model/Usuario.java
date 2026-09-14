package com.tecnogestion.model;

public class Usuario {

    private String usuario;
    private String password;
    private String nombreCompleto;

    public Usuario() {
    }

    public Usuario(String usuario, String password, String nombreCompleto) {
        this.usuario = usuario;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}

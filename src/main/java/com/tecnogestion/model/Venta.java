package com.tecnogestion.model;

import java.time.LocalDate;

public class Venta {

    private Long id;
    private String cliente;
    private String producto;
    private int cantidad;
    private double total;
    private LocalDate fecha;

    public Venta() {
    }

    public Venta(Long id, String cliente, String producto, int cantidad, double total, LocalDate fecha) {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

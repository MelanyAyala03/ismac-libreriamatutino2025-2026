package com.distribuida.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "factura_detalle")
public class FacturaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura_detalle")
    @JsonProperty("id_factura_detalle")
    private int idFacturaDetalle;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "subtotal")
    private float subtotal;

    // relación JPA (NO se muestra completa)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_factura")
    private Factura factura;

    // relación JPA (NO se muestra completa)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

    // 👉 SOLO PARA JSON (id_factura)
    @JsonProperty("id_factura")
    public int getIdFactura() {
        return factura != null ? factura.getIdFactura() : 0;
    }

    // 👉 SOLO PARA JSON (id_libro)
    @JsonProperty("id_libro")
    public int getIdLibro() {
        return libro != null ? libro.getIdLibro() : 0;
    }

    public FacturaDetalle() {}

    public FacturaDetalle(int idFacturaDetalle, int cantidad, float subtotal, Factura factura, Libro libro) {
        this.idFacturaDetalle = idFacturaDetalle;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.factura = factura;
        this.libro = libro;
    }

    public int getIdFacturaDetalle() {
        return idFacturaDetalle;
    }

    public void setIdFacturaDetalle(int idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}

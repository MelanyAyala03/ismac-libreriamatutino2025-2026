package com.distribuida.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name="factura")
public class Factura {
//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private int idFactura;
    @Column(name = "num_factura")
    private String numFactura;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "total_neto")
    private Double totalNeto;
    @Column(name = "iva")
    private Double iva;
    @Column(name = "total")
    private Double total;

    //private int idCliente no
        //inyeccion de dependencias
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private cliente Cliente;

    public Factura(){}

    public Factura(int idFactura, String numFactura, Date fecha, Double totalNeto, Double iva, Double total, cliente cliente) {
        this.idFactura = idFactura;
        this.numFactura = numFactura;
        this.fecha = fecha;
        this.totalNeto = totalNeto;
        this.iva = iva;
        this.total = total;
        Cliente = cliente;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(Double totalNeto) {
        this.totalNeto = totalNeto;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public cliente getCliente() {
        return Cliente;
    }

        //metodo inyector por setter
    public void setCliente(cliente cliente) {
        Cliente = cliente;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", numFactura='" + numFactura + '\'' +
                ", fecha=" + fecha +
                ", totalNeto=" + totalNeto +
                ", iva=" + iva +
                ", total=" + total +
                ", Cliente=" + Cliente +
                '}';
    }
}

package com.distribuida.principal;

import com.distribuida.model.Factura;
import com.distribuida.model.cliente;

import java.time.LocalDateTime;
import java.util.Date;

public class FacturaPrincipal {
    public static void main(String[] args) {
        cliente Cliente = new cliente("1726813684", 1, "Mateo", "Benitez", "AV. queti y sapo", "0997615005", "mateo78_ricardo@hotmail.com");
        Factura factura = new Factura(1, "FAC-001", new Date(), 100.00, 12.00, 112.00, Cliente);
        System.out.println(factura.toString());
    }
}

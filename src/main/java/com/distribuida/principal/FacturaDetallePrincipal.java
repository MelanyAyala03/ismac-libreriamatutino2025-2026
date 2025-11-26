package com.distribuida.principal;

import com.distribuida.model.*;

import java.util.Date;

public class FacturaDetallePrincipal {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("1726813684", 1, "Mateo", "Benitez", "AV. queti y sapo", "0997615005", "mateo78_ricardo@hotmail.com");
        Factura factura = new Factura(1, "FAC-001", new Date(), 100.00, 12.00, 112.00, cliente);
        Categoria categoria = new Categoria(1, "Novela", "Libros de ficción");
        Autor autor = new Autor(1, "Gabriel", "García Márquez", "Colombia", "Calle 123", "0991234567", "gabriel@gmail.com");
        Libro libro = new Libro(1, "Cien años de soledad", "Sudamericana", 471, "1ra", "Español", java.time.LocalDateTime.of(1967, 5, 30, 0, 0),
                "Novela histórica", "Dura", "978-84-376-0494-7", 50, "portada.jpg", "Tapa dura", 20.5f, categoria, autor);

        FacturaDetalle detalle = new FacturaDetalle(1, 2, 41.0f, factura, libro);
        System.out.println(detalle.toString());
    }
}
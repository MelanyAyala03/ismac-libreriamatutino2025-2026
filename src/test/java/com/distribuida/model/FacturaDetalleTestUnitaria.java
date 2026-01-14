package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaDetalleTestUnitaria {

    private FacturaDetalle Detalle;
    private Libro Libro;
    private Factura Factura;

    @BeforeEach
    public void setup() {

        Libro = new Libro();
        Libro.setTitulo("Libro test");
        Libro.setPrecio(20);

        Factura = new Factura();
        Factura.setNumFactura("FAC-010");

        Detalle = new FacturaDetalle();
        Detalle.setIdFacturaDetalle(1);
        Detalle.setCantidad(3);
        Detalle.setSubtotal(60);
        Detalle.setLibro(Libro);
        Detalle.setFactura(Factura);
    }

    @Test
    public void testFacturaDetalleConstructor() {
        assertAll("Validar Constructor - factura_detalle",
                () -> assertEquals(1, Detalle.getIdFacturaDetalle()),
                () -> assertEquals(3, Detalle.getCantidad()),
                () -> assertEquals(60, Detalle.getSubtotal())
        );
    }

    @Test
    public void testFacturaDetalleInyector() {
        assertAll("Validar inyector - factura_detalle",
                () -> assertNotNull(Detalle.getLibro()),
                () -> assertNotNull(Detalle.getFactura()),
                () -> assertEquals("Libro test", Detalle.getLibro().getTitulo()),
                () -> assertEquals("FAC-010", Detalle.getFactura().getNumFactura())
        );
    }

    @Test
    public void testFacturaDetalleToString() {
        String s = Detalle.toString();

        assertAll("Validar toString - factura_detalle",
                () -> assertTrue(s.contains("1")),
                () -> assertTrue(s.contains("3"))
        );
    }

    @Test
    public void testFacturaDetalleValoresNegativos() {

        Detalle.setSubtotal(-60);

        assertAll("Validar valores negativos - factura_detalle",
                () -> assertEquals(-60, Detalle.getSubtotal())
        );
    }

}

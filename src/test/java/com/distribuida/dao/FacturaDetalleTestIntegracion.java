package com.distribuida.dao;

import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(false)
public class FacturaDetalleTestIntegracion {

    @Autowired
    FacturaDetalleDAO facturaDetalleDAO;

    @Autowired
    FacturaDAO facturaDAO;

    @Autowired
    LibroDAO libroDAO;

    @Test
    public void testFindAll() {
        List<FacturaDetalle> detalles = facturaDetalleDAO.findAll();
        assertNotNull(detalles);
        assertTrue(detalles.size() > 0);
    }

    @Test
    public void testFindOne() {
        Optional<FacturaDetalle> detalleOpt = facturaDetalleDAO.findById(1);
        assertTrue(detalleOpt.isPresent());
        System.out.println(detalleOpt.get());
    }

    @Test
    public void testSave() {
        Factura factura = facturaDAO.findById(1).orElse(null);
        Libro libro = libroDAO.findById(1).orElse(null);

        FacturaDetalle detalle = new FacturaDetalle();
        detalle.setCantidad(2);
        detalle.setSubtotal(50);
        detalle.setFactura(factura);
        detalle.setLibro(libro);

        FacturaDetalle guardado = facturaDetalleDAO.save(detalle);

        assertNotNull(guardado);
        assertEquals(2, guardado.getCantidad());
    }

    @Test
    public void testUpdate() {
        Optional<FacturaDetalle> detalleOpt = facturaDetalleDAO.findById(1);
        assertTrue(detalleOpt.isPresent());

        detalleOpt.get().setCantidad(10);

        FacturaDetalle actualizado = facturaDetalleDAO.save(detalleOpt.get());

        assertEquals(10, actualizado.getCantidad());
    }

    @Test
    public void testDelete() {
        if (facturaDetalleDAO.existsById(20)) {
            facturaDetalleDAO.deleteById(20);
        }
        assertFalse(facturaDetalleDAO.existsById(20));
    }
}

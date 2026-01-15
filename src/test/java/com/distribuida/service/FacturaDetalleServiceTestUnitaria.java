package com.distribuida.service;

import com.distribuida.Service.FacturaDetalleServiceImpl;
import com.distribuida.dao.FacturaDetalleDAO;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
//
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaDetalleServiceTestUnitaria {

    @Mock
    private FacturaDetalleDAO facturaDetalleDAO;

    @InjectMocks
    private FacturaDetalleServiceImpl facturaDetalleService;

    private FacturaDetalle facturaDetalle;
    private Factura factura;
    private Libro libro;

    @BeforeEach
    public void setUp() {
        factura = new Factura();
        factura.setIdFactura(1);

        libro = new Libro();
        libro.setIdLibro(1);

        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(2);
        facturaDetalle.setSubtotal(50.0f);
        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);
    }


    @Test
    public void findAll() {
        when(facturaDetalleDAO.findAll()).thenReturn(List.of(facturaDetalle));

        List<FacturaDetalle> detalles = facturaDetalleService.findAll();

        assertNotNull(detalles);
        assertEquals(1, detalles.size());
        verify(facturaDetalleDAO, times(1)).findAll();
    }

    @Test
    public void findOneExistente() {
        when(facturaDetalleDAO.findById(1)).thenReturn(Optional.of(facturaDetalle));

        Optional<FacturaDetalle> resultado = facturaDetalleService.findOne(1);

        assertNotNull(resultado);
        assertEquals(2, resultado.orElse(null).getCantidad());
    }

    @Test
    public void findOneNoExistente() {
        when(facturaDetalleDAO.findById(999)).thenReturn(Optional.empty());

        Optional<FacturaDetalle> resultado = facturaDetalleService.findOne(999);

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void save() {
        when(facturaDetalleDAO.save(facturaDetalle)).thenReturn(facturaDetalle);

        FacturaDetalle resultado = facturaDetalleService.save(facturaDetalle);

        assertNotNull(resultado);
        assertEquals(50.0f, resultado.getSubtotal());
    }

    @Test
    public void updateExistente() {
        FacturaDetalle detalleActualizado = new FacturaDetalle();
        detalleActualizado.setCantidad(3);
        detalleActualizado.setSubtotal(75.0f);
        detalleActualizado.setFactura(factura);
        detalleActualizado.setLibro(libro);

        when(facturaDetalleDAO.findById(1)).thenReturn(Optional.of(facturaDetalle));
        when(facturaDetalleDAO.save(any())).thenReturn(detalleActualizado);

        FacturaDetalle resultado = facturaDetalleService.update(1, detalleActualizado);

        assertNotNull(resultado);
        assertEquals(3, resultado.getCantidad());
        verify(facturaDetalleDAO, times(1)).save(facturaDetalle);
    }

    @Test
    public void updateNoExistente() {
        FacturaDetalle nuevo = new FacturaDetalle();

        when(facturaDetalleDAO.findById(999)).thenReturn(Optional.empty());

        FacturaDetalle resultado = facturaDetalleService.update(999, nuevo);

        assertNull(resultado);
        verify(facturaDetalleDAO, never()).save(any());
    }


    @Test
    public void deleteExistente() {
        when(facturaDetalleDAO.existsById(1)).thenReturn(true);

        facturaDetalleService.delete(1);

        verify(facturaDetalleDAO).deleteById(1);
    }

    @Test
    public void deleteNoExistente() {
        when(facturaDetalleDAO.existsById(999)).thenReturn(false);

        facturaDetalleService.delete(999);

        verify(facturaDetalleDAO, never()).deleteById(anyInt());
    }
}

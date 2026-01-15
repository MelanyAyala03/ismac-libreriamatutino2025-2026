package com.distribuida.service;

import com.distribuida.Service.FacturaServiceImpl;
import com.distribuida.dao.FacturaDAO;
import com.distribuida.model.Factura;
import com.distribuida.model.cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTestUnitaria {

    @Mock
    private FacturaDAO facturaDAO;

    @InjectMocks
    private FacturaServiceImpl facturaService;

    private Factura factura;
    private cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new cliente(
                "1728437862",
                1,
                "Melany",
                "Ayala",
                "Mas alla",
                "0984605405",
                "melany@correo.com"
        );

        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.0);
        factura.setIva(15.0);
        factura.setTotal(115.0);
        factura.setCliente(cliente);
    }

    @Test
    void findAll() {
        when(facturaDAO.findAll()).thenReturn(List.of(factura));

        List<Factura> resultado = facturaService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(facturaDAO).findAll();
    }

    @Test
    void findOneExistente() {
        when(facturaDAO.findById(1)).thenReturn(Optional.of(factura));

        Optional<Factura> resultado = facturaService.findOne(1);

        assertTrue(resultado.isPresent());
        assertEquals("FAC-0001", resultado.get().getNumFactura());
    }

    @Test
    void findOneNoExistente() {
        when(facturaDAO.findById(99)).thenReturn(Optional.empty());

        Optional<Factura> resultado = facturaService.findOne(99);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void save() {
        when(facturaDAO.save(factura)).thenReturn(factura);

        Factura resultado = facturaService.save(factura);

        assertNotNull(resultado);
        assertEquals("FAC-0001", resultado.getNumFactura());
    }
//
    @Test
    void updateExistente() {
        Factura nueva = new Factura();
        nueva.setNumFactura("FAC-0002");
        nueva.setFecha(new Date());
        nueva.setTotalNeto(200.0);
        nueva.setIva(30.0);
        nueva.setTotal(230.0);
        nueva.setCliente(cliente);

        when(facturaDAO.findById(1)).thenReturn(Optional.of(factura));
        when(facturaDAO.save(any(Factura.class))).thenReturn(nueva);

        Factura resultado = facturaService.update(1, nueva);

        assertNotNull(resultado);
        assertEquals("FAC-0002", resultado.getNumFactura());
        verify(facturaDAO).save(factura);
    }

    @Test
    void updateNoExistente() {
        when(facturaDAO.findById(999)).thenReturn(Optional.empty());

        Factura resultado = facturaService.update(999, new Factura());

        assertNull(resultado);
        verify(facturaDAO, never()).save(any());
    }

    @Test
    void deleteExistente() {
        when(facturaDAO.existsById(1)).thenReturn(true);

        facturaService.delete(1);

        verify(facturaDAO).deleteById(1);
    }

    @Test
    void deleteNoExistente() {
        when(facturaDAO.existsById(999)).thenReturn(false);

        facturaService.delete(999);

        verify(facturaDAO, never()).deleteById(anyInt());
    }
}

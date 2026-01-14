package com.distribuida.dao;

import com.distribuida.model.Factura;
import com.distribuida.model.cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class FacturaTestIntegracion {

    @Autowired
    private FacturaDAO facturaDAO;

    @Autowired
    private ClienteDAO clienteDAO;

    @Test
    public void testFacturaFindAll(){
        List<Factura> facturas = facturaDAO.findAll();
        assertNotNull(facturas);
        assertTrue(facturas.size() > 0);
        facturas.forEach(System.out::println);
    }

    @Test
    public void testFacturaFindOne(){
        Optional<Factura> factura = facturaDAO.findById(1);
        assertTrue(factura.isPresent());
        assertEquals("FAC-0001", factura.orElse(null).getIdFactura());
        //assertEquals("150,96", factura.orElse(null).getTotal());
        System.out.println(factura.toString());

        //150.96 no reconoce 2 sifraz decimales - validar metodos de precision decimal
    }

    @Test
    public void testFacturaSave(){
        Optional<cliente> cliente = clienteDAO.findById(1);

        assertTrue(cliente.isPresent());

        Factura factura = new Factura();
        factura.setIdFactura(0);
        factura.setNumFactura("FAC-00066");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.0);
        factura.setIva(15.0);
        factura.setTotal(115.0);
        factura.setCliente(cliente.orElse(null));

        Factura facturaGuardada = facturaDAO.save(factura);
        assertNotNull(facturaGuardada);
        assertEquals("FAC-00066", facturaGuardada.getNumFactura());
        assertEquals("100.0", facturaGuardada.getTotalNeto());
    }

    @Test
    public void testFacturaUpdate(){
        Optional<cliente> cliente = clienteDAO.findById(2);

        assertTrue(cliente.isPresent());

        Optional<Factura> factura = facturaDAO.findById(87);

        assertTrue(factura.isPresent());

        factura.orElse(null).setNumFactura("FAC-00077");
        factura.orElse(null).setFecha(new Date());
        factura.orElse(null).setTotalNeto(200.00);
        factura.orElse(null).setIva(60.00);
        factura.orElse(null).setTotal(260.00);
        factura.orElse(null).setCliente(cliente.orElse(null));

        Factura facturaActualizada = facturaDAO.save(factura.orElse(null));

        assertEquals("FAC-00077", facturaActualizada.getNumFactura());
        assertEquals("200.0", facturaActualizada.getTotalNeto());
        assertEquals("Juan", facturaActualizada.getCliente().getNombre());

    }

    @Test
    public void testFacturaDelete(){

        if (facturaDAO.existsById(87)){
            facturaDAO.deleteById(87);
        }

        assertFalse(facturaDAO.existsById(87), "***************** El Dato Fue Eliminado **********************");

    }

}

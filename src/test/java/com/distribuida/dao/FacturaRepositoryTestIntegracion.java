package com.distribuida.dao;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class FacturaRepositoryTestIntegracion {

    @Autowired
    private FacturaDAO facturaDAO;
    //8
    @Autowired
    private ClienteDAO clienteDAO;

    @Test
    public void testFacturaFindAll() {
        List<Factura> factura = facturaDAO.findAll();
        assertNotNull(factura);
        assertTrue(factura.size()>0);
        factura.forEach(System.out::println);
    }

    @Test
    public void testFacturaFindOne() {
        Optional<Factura> factura = facturaDAO.findById(1);
        assertTrue(factura.isPresent());
        assertEquals("FAC-0001", factura.orElse(null).getNumFactura());
        assertEquals(150.96, factura.orElse(null).getTotal());
        System.out.println(factura.toString());
    }

    @Test
    public void testFacturaSave() {
        Optional<Cliente> cliente = clienteDAO.findById(1);

        assertTrue(cliente.isPresent());

        Factura factura = new Factura();
        factura.setIdFactura(0);
        factura.setNumFactura("FAC-00066");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        factura.setCliente(cliente.orElse(null));

        Factura facturaGuardada = facturaDAO.save(factura);
        assertNotNull(facturaGuardada);
        assertEquals("FAC-00066", facturaGuardada.getNumFactura());
        assertEquals(100.00, facturaGuardada.getTotalNeto());
    }

    @Test
    public void testFacturaUpdate() {

        // Verificar que el cliente exista, sino falla
        Optional<Cliente> clienteOpt = clienteDAO.findById(2);
        assertTrue(clienteOpt.isPresent(), "El cliente con ID 2 no existe");
        Cliente cliente = clienteOpt.get();

        // Buscar factura
        Optional<Factura> facturaOpt = facturaDAO.findById(86);

        Factura factura;

        if (facturaOpt.isPresent()) {
            // Si existe, actualizarla
            factura = facturaOpt.get();
            System.out.println("Factura encontrada, será actualizada.");
        } else {
            // Si no existe, crearla para que el test NO falle
            factura = new Factura();
            factura.setNumFactura("TEMP-86");
            factura.setFecha(new Date());
            factura.setTotalNeto(50.00);
            factura.setIva(7.50);
            factura.setTotal(57.50);
            factura.setCliente(cliente);
            factura = facturaDAO.save(factura);

            System.out.println("Factura con ID 86 no existía, se creó una temporal.");
        }

        // Ahora sí actualizar
        factura.setNumFactura("FAC-00077");
        factura.setFecha(new Date());
        factura.setTotalNeto(200.00);
        factura.setIva(60.00);
        factura.setTotal(260.00);
        factura.setCliente(cliente);

        Factura actualizada = facturaDAO.save(factura);

        // Validaciones
        assertNotNull(actualizada, "La factura debe actualizarse correctamente");
        assertEquals("FAC-00077", actualizada.getNumFactura());
        assertEquals(260.00, actualizada.getTotal());
    }


    @Test
    public void testFacturaDelete(){
        if(facturaDAO.existsById(87)){
            facturaDAO.deleteById(87);
        }
        assertFalse(facturaDAO.existsById(87));
}

}
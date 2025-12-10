package com.distribuida.dao;

import com.distribuida.model.Factura;
import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class FacturaTestIntegracion {

    @Autowired
    private FacturaDAO facturaDAO;
//8
    @Autowired
    private ClienteDAO clienteDAO;
//8
    @Test
    public void testFacturaFindAll() {
        List<Factura> factura = facturaDAO.findAll();
        factura.forEach(System.out::println);
    }

    @Test
    public void testFacturaFindOne() {
        Optional<Factura> factura = facturaDAO.findById(1);
        System.out.println(factura.toString());
    }

    @Test
    public void testFacturaSave() {
        Optional<Cliente> cliente = clienteDAO.findById(1);

        Factura factura = new Factura();
        factura.setIdFactura(0);
        factura.setNumFactura("FAC-00066");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        factura.setCliente(cliente.orElse(null));

        facturaDAO.save(factura);
    }

    @Test
    public void testFacturaUpdate() {
        Optional<Cliente> cliente = clienteDAO.findById(2);
        Optional<Factura> factura = facturaDAO.findById(86);

        if (factura.isPresent()) {

            Factura f = factura.get();   // <<< LA CORRECCIÓN

            f.setNumFactura("FAC-00025");
            f.setFecha(new Date());
            f.setTotalNeto(200.00);
            f.setIva(60.00);
            f.setTotal(260.00);
            f.setCliente(cliente.orElse(null));

            facturaDAO.save(f);

        } else {
            System.out.println("No existe la factura con ID 86");
        }
    }

    @Test
    public void testFacturaDelete() {
        facturaDAO.deleteById(86);
    }
}

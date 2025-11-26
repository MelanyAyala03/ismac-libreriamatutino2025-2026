package com.distribuida.dao;

import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Factura;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class FacturaDetalleTestintegracion {

    @Autowired
    private FacturaDetalleDAO facturaDetalleDAO;

    @Autowired
    private FacturaDAO facturaDAO;

    @Autowired
    private LibroDAO libroDAO;

    @Test
    public void findAll(){
        List<FacturaDetalle> detalles = facturaDetalleDAO.findAll();
        detalles.forEach(System.out::println);
    }

    @Test
    public void findOne(){
        Optional<FacturaDetalle> detalle = facturaDetalleDAO.findById(1);
        System.out.println(detalle.orElse(null));
    }

    @Test
    public void save(){
        Factura factura = facturaDAO.findById(1).orElse(null);
        Libro libro = libroDAO.findById(1).orElse(null);

        FacturaDetalle detalle = new FacturaDetalle();
        detalle.setCantidad(2);
        detalle.setSubtotal(40.50F);
        detalle.setFactura(factura);
        detalle.setLibro(libro);

        facturaDetalleDAO.save(detalle);
    }

    @Test
    public void update(){
        Optional<FacturaDetalle> detalle = facturaDetalleDAO.findById(1);

        if(detalle.isPresent()){
            FacturaDetalle d = detalle.get();

            d.setCantidad(5);
            //8d.setSubtotal(99.90F);

            facturaDetalleDAO.save(d);

        } else {
            System.out.println("No existe el detalle con ID 1");
        }
    }

    @Test
    public void delete(){
        facturaDetalleDAO.deleteById(1);
    }
}

package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaTestUnitaria {

    private Factura factura;
    private Cliente Cliente;
//8
    @BeforeEach
    public void setup(){


            Cliente = new Cliente("171960400"
                    , 1
                    , "Matias"
                    , "Noguera"
                    , "Av. queti y sapo"
                    , "0968339777"
                    , "matino@hotmail.com");


        factura = new Factura();

        factura.setIdFactura(1);
        factura.setNumFactura("FAC-001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
            //Inyeccion
        factura.setCliente(Cliente);
    }

    @Test
    public void testFacturaConstructor(){
        assertAll("Valida datos de Constructor - factura",
                () -> assertEquals(1,factura.getIdFactura()),
                () -> assertEquals("FAC-001", factura.getNumFactura()),
                () -> assertEquals(100.00, factura.getTotalNeto()),
                () -> assertEquals(15.00, factura.getIva()),
                () -> assertEquals(115.00, factura.getTotal())
                );
    }

    @Test
    public void TestFacturaSetters(){
        Cliente = new Cliente("1726813685",2,"Matias2","Noguera2","Direccion2","0968339777","mateino@hotmail.com2");
        factura = new Factura();

        factura.setIdFactura(2);
        factura.setNumFactura("FAC-002");
        factura.setFecha(new Date());
        factura.setTotalNeto(200.00);
        factura.setIva(30.00);
        factura.setTotal(230.00);
        //Test en inyeccion
        factura.setCliente(Cliente);

        assertAll("Validar metodos setters - Factura",
                () -> assertEquals(2, factura.getIdFactura()),
                () -> assertEquals("FAC-002", factura.getNumFactura()),
                () -> assertEquals(200.00, factura.getTotalNeto()),
                () -> assertEquals(30.00, factura.getIva()),
                () -> assertEquals(230.00, factura.getTotal()),
                () -> assertEquals("1726813685", factura.getCliente().getCedula())
        );
    }

    @Test
    public void testFacturaToString(){
        String str = factura.toString();

        assertAll("Validar datos de toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("FAC-001")),
                () -> assertTrue(str.contains("100.0")),
                () -> assertTrue(str.contains("15.0")),
                () -> assertTrue(str.contains("115.0")),
                () -> assertTrue(str.contains("Matias"))
                );
    }

    @Test
    public void testFacturaInyector(){

        assertAll("Validar metodo inyector",
                () -> assertNotNull(factura.getCliente()),
                () -> assertEquals("Noguera", factura.getCliente().getApellido())
                );
    }

    @Test
    public void testFacturaValoresNegativos(){

        factura.setTotal(-100.00);
        assertAll("Validar datos Negativos - Factura",
                () -> assertEquals(-100.00, factura.getTotal())
                );
            // validar que se evite valores negativos
    }

}

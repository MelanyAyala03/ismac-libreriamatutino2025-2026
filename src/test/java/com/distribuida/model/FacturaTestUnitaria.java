package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaTestUnitaria {

    private Factura factura;
    private cliente Cliente;

    @BeforeEach
    public void setup(){

        Cliente = new cliente("1726813684"
                ,1
                ,"Mateo"
                ,"Benitez"
                ,"Av. queti y sapo"
                ,"0997615005"
                ,"mateo78_ricardo@hotmail.com"
        );

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
        Cliente = new cliente("1726813685",2,"Mateo2","Benitez2","Direccion2","0997615006","mateo78_ricardo@hotmail.com2");
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
                () -> assertTrue(str.contains("Mateo"))
                );
    }

    @Test
    public void testFacturaInyector(){

        assertAll("Validar metodo inyector",
                () -> assertNotNull(factura.getCliente()),
                () -> assertEquals("Benitez", factura.getCliente().getApellido())
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

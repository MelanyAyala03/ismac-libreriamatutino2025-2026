package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTestUnitaria {

    private cliente Cliente;

    @BeforeEach
    public void setup(){
        Cliente = new cliente("1726813684"
        , 1
        , "Mateo"
        , "Benitez"
        , "Av. queti y sapo"
        , "0997615005"
        , "mateo78_ricardo@hotmail.com");
    }

    @Test
    public void setClienteConstructor(){

        assertAll("Validar datos del cliente con constructor",
                () -> assertEquals(1, Cliente.getIdCliente()),
                () -> assertEquals("1726813684", Cliente.getCedula()),
                () -> assertEquals("Mateo", Cliente.getNombre()),
                () -> assertEquals("Benitez", Cliente.getApellido()),
                () -> assertEquals("Av. queti y sapo", Cliente.getDireccion()),
                () -> assertEquals("0997615005", Cliente.getTelefono()),
                () -> assertEquals("mateo78_ricardo@hotmail.com", Cliente.getCorreo())
        );

    }

    @Test
    public void testClienteSetters(){

        Cliente.setIdCliente(2);
        Cliente.setCedula("1726843684");
        Cliente.setNombre("Julio");
        Cliente.setApellido("Profe");
        Cliente.setDireccion("Su casa");
        Cliente.setTelefono("29954459");
        Cliente.setCorreo("JProfe@hotmail.com");

        assertAll("Validar datos del cliente consetters",
                () -> assertEquals(2, Cliente.getIdCliente()),
                () -> assertEquals("1726843684", Cliente.getCedula()),
                () -> assertEquals("Julio", Cliente.getNombre()),
                () -> assertEquals("Profe", Cliente.getApellido()),
                () -> assertEquals("Su casa", Cliente.getDireccion()),
                () -> assertEquals("29954459", Cliente.getTelefono()),
                () -> assertEquals("JProfe@hotmail.com", Cliente.getCorreo())
                );

    }

    @Test
    public void testclientToString() {

        String str = Cliente.toString();

        assertAll("Validar datos del cliente en toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1726813684")),
                () -> assertTrue(str.contains("Mateo")),
                () -> assertTrue(str.contains("Benitez")),
                () -> assertTrue(str.contains("Av. queti y sapo")),
                () -> assertTrue(str.contains("0997615005")),
                () -> assertTrue(str.contains("mateo78_ricardo@hotmail.com"))
                );
    }
}

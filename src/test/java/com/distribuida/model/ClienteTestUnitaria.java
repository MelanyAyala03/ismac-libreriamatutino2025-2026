package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTestUnitaria {

    private Cliente Cliente;

    @BeforeEach
    public void setup(){
        Cliente = new Cliente("171960400"
        , 1
        , "Matias"
        , "Noguera"
        , "Av. queti y sapo"
        , "0968339777"
        , "matino@hotmail.com");
    }

    @Test
    public void setClienteConstructor(){

        assertAll("Validar datos del cliente con constructor",
                () -> assertEquals(1, Cliente.getIdCliente()),
                () -> assertEquals("171960400", Cliente.getCedula()),
                () -> assertEquals("Matias", Cliente.getNombre()),
                () -> assertEquals("Noguera", Cliente.getApellido()),
                () -> assertEquals("Av. queti y sapo", Cliente.getDireccion()),
                () -> assertEquals("0968339777", Cliente.getTelefono()),
                () -> assertEquals("matino@hotmail.com", Cliente.getCorreo())
        );

    }

    @Test
    public void testClienteSetters(){

        Cliente.setIdCliente(2);
        Cliente.setCedula("178945612");
        Cliente.setNombre("Julia");
        Cliente.setApellido("Licen");
        Cliente.setDireccion("Su casa");
        Cliente.setTelefono("2381095");
        Cliente.setCorreo("JLicen@hotmail.com");

        assertAll("Validar datos del cliente consetters",
                () -> assertEquals(2, Cliente.getIdCliente()),
                () -> assertEquals("178945612", Cliente.getCedula()),
                () -> assertEquals("Julia", Cliente.getNombre()),
                () -> assertEquals("Licen", Cliente.getApellido()),
                () -> assertEquals("Su casa", Cliente.getDireccion()),
                () -> assertEquals("2381095", Cliente.getTelefono()),
                () -> assertEquals("JLicen@hotmail.com", Cliente.getCorreo())
                );

    }

    @Test
    public void testclientToString() {

        String str = Cliente.toString();

        assertAll("Validar datos del cliente en toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("171960400")),
                () -> assertTrue(str.contains("Matias")),
                () -> assertTrue(str.contains("Noguera")),
                () -> assertTrue(str.contains("Av. queti y sapo")),
                () -> assertTrue(str.contains("0968339777")),
                () -> assertTrue(str.contains("matino@hotmail.com"))
                );
    }
}

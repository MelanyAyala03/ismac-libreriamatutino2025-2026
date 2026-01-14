package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutorTestUnitaria {

    private Autor Autor;

    @BeforeEach
    public void setup() {
        Autor = new Autor(
                1,
                "Gabriel",
                "García",
                "Colombia",
                "Direccion X",
                "099999999",
                "gabo@test.com"
        );
    }

    @Test
    public void testAutorConstructor() {
        assertAll("Validar Constructor - autor",
                () -> assertEquals(1, Autor.getIdAutor()),
                () -> assertEquals("Gabriel", Autor.getNombre()),
                () -> assertEquals("García", Autor.getApellido()),
                () -> assertEquals("Colombia", Autor.getPais())
        );
    }

    @Test
    public void TestAutorSetters() {
        Autor = new Autor();
        Autor.setIdAutor(2);
        Autor.setNombre("Julio");
        Autor.setApellido("Cortázar");

        assertAll("Validar setters - autor",
                () -> assertEquals(2, Autor.getIdAutor()),
                () -> assertEquals("Julio", Autor.getNombre()),
                () -> assertEquals("Cortázar", Autor.getApellido())
        );
    }

    @Test
    public void testAutorToString() {
        String s = Autor.toString();

        assertAll("Validar toString - autor",
                () -> assertTrue(s.contains("Gabriel")),
                () -> assertTrue(s.contains("García"))
        );
    }

    @Test
    public void testAutorValoresNegativos() {

        Autor.setIdAutor(-10);

        assertAll("Validar valores negativos - autor",
                () -> assertEquals(-10, Autor.getIdAutor())
        );
    }
}

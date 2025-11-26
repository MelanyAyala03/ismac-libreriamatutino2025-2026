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
                "Melany",
                "Ayala",
                "Ecuador",
                "Av.mas alla",
                "0985614851",
                "melay@test.com"
        );
    }

    @Test
    public void testAutorConstructor() {
        assertAll("Validar Constructor - autor",
                () -> assertEquals(1, Autor.getIdAutor()),
                () -> assertEquals("Melany", Autor.getNombre()),
                () -> assertEquals("Ayala", Autor.getApellido()),
                () -> assertEquals("Ecuador", Autor.getPais())
        );
    }

    @Test
    public void TestAutorSetters() {
        Autor = new Autor();
        Autor.setIdAutor(2);
        Autor.setNombre("Julia");
        Autor.setApellido("Corteza");

        assertAll("Validar setters - autor",
                () -> assertEquals(2, Autor.getIdAutor()),
                () -> assertEquals("Julia", Autor.getNombre()),
                () -> assertEquals("Corteza", Autor.getApellido())
        );
    }

    @Test
    public void testAutorToString() {
        String s = Autor.toString();

        assertAll("Validar toString - autor",
                () -> assertTrue(s.contains("Melany")),
                () -> assertTrue(s.contains("Ayala"))
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

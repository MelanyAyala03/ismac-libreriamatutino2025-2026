package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaTestUnitaria {

    private Categoria Categoria;

    @BeforeEach
    public void setup() {
        Categoria = new Categoria(1, "Ficción", "Narrativa literaria");
    }

    @Test
    public void testCategoriaConstructor() {
        assertAll("Validar Constructor - categoria",
                () -> assertEquals(1, Categoria.getIdCategoria()),
                () -> assertEquals("Ficción", Categoria.getCategoria()),
                () -> assertEquals("Narrativa literaria", Categoria.getDescripcion())
        );
    }

    @Test
    public void TestCategoriaSetters() {
        Categoria = new Categoria();
        Categoria.setIdCategoria(2);
        Categoria.setCategoria("Tecnología");
        Categoria.setDescripcion("Libros técnicos");

        assertAll("Validar setters - categoria",
                () -> assertEquals(2, Categoria.getIdCategoria()),
                () -> assertEquals("Tecnología", Categoria.getCategoria()),
                () -> assertEquals("Libros técnicos", Categoria.getDescripcion())
        );
    }

    @Test
    public void testCategoriaToString() {
        String s = Categoria.toString();

        assertAll("Validar toString - categoria",
                () -> assertTrue(s.contains("Ficción")),
                () -> assertTrue(s.contains("Narrativa literaria"))
        );
    }

    @Test
    public void testCategoriaValoresNegativos() {

        Categoria.setIdCategoria(-1);

        assertAll("Validar valores negativos - categoria",
                () -> assertEquals(-1, Categoria.getIdCategoria())
        );
    }
}

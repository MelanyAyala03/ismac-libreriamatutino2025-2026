package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaTestUnitaria {
//8
    private Categoria Categoria;

    @BeforeEach
    public void setup() {
        Categoria = new Categoria(1, "Drama", "Narrativa literaria");
    }

    @Test
    public void testCategoriaConstructor() {
        assertAll("Validar Constructor - categoria",
                () -> assertEquals(1, Categoria.getIdCategoria()),
                () -> assertEquals("Drama", Categoria.getCategoria()),
                () -> assertEquals("Narrativa literaria", Categoria.getDescripcion())
        );
    }

    @Test
    public void TestCategoriaSetters() {
        Categoria = new Categoria();
        Categoria.setIdCategoria(2);
        Categoria.setCategoria("Administracion");
        Categoria.setDescripcion("Libros calculos");

        assertAll("Validar setters - categoria",
                () -> assertEquals(2, Categoria.getIdCategoria()),
                () -> assertEquals("Administracion", Categoria.getCategoria()),
                () -> assertEquals("Libros calculos", Categoria.getDescripcion())
        );
    }

    @Test
    public void testCategoriaToString() {
        String s = Categoria.toString();

        assertAll("Validar toString - categoria",
                () -> assertTrue(s.contains("Drama")),
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

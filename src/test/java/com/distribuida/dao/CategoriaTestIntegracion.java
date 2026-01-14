package com.distribuida.dao;

import com.distribuida.model.Categoria;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(false)
public class CategoriaTestIntegracion {

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Test
    public void testFindAll() {
        List<Categoria> categorias = categoriaDAO.findAll();
        assertNotNull(categorias);
        assertTrue(categorias.size() > 0);
        categorias.forEach(System.out::println);
    }

    @Test
    public void testFindOne() {
        Optional<Categoria> categoriaOpt = categoriaDAO.findById(1);
        assertTrue(categoriaOpt.isPresent());
        System.out.println(categoriaOpt.get());
    }

    @Test
    public void testSave() {
        Categoria categoria = new Categoria(0,"Fantasia", "Libros de magia y mundos imaginarios");
        Categoria guardada = categoriaDAO.save(categoria);

        assertNotNull(guardada);
        assertEquals("Fantasia", guardada.getCategoria());
    }

    @Test
    public void testUpdate() {
        Optional<Categoria> categoriaOpt = categoriaDAO.findById(1);
        assertTrue(categoriaOpt.isPresent());

        categoriaOpt.get().setDescripcion("Descripción actualizada");

        Categoria actualizada = categoriaDAO.save(categoriaOpt.get());
        assertEquals("Descripción actualizada", actualizada.getDescripcion());
    }

    @Test
    public void testDelete() {
        if (categoriaDAO.existsById(10)) {
            categoriaDAO.deleteById(10);
        }
        assertFalse(categoriaDAO.existsById(10));
    }
}

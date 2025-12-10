package com.distribuida.dao;

import com.distribuida.model.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CategoriaRepositoryTestIntegracion {

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Test
    public void findAll() {
        List<Categoria> categorias = categoriaDAO.findAll();
        assertNotNull(categorias);
        categorias.forEach(System.out::println);
    }

    @Test
    public void findOne() {
        Optional<Categoria> categoria = categoriaDAO.findById(1);

        if (categoria.isPresent()) {
            System.out.println("Categoría encontrada: " + categoria.get());
        } else {
            System.out.println("No existe categoría con ID 1");
        }
    }

    @Test
    public void save() {
        Categoria categoria = new Categoria();
        categoria.setNombre("Terror");

        Categoria guardado = categoriaDAO.save(categoria);
        assertNotNull(guardado);

        System.out.println("Guardado: " + guardado);
    }

    @Test
    public void update() {
        Optional<Categoria> categoria = categoriaDAO.findById(1);

        if (categoria.isPresent()) {
            Categoria cat = categoria.get();
            cat.setNombre("Editada");
            categoriaDAO.save(cat);

            System.out.println("Categoría actualizada: " + cat);
        } else {
            System.out.println("No existe categoría con ID 1");
        }
    }

    @Test
    public void delete() {
        categoriaDAO.deleteById(1);
        System.out.println("Categoría con ID 1 eliminada (si existía).");
    }
}

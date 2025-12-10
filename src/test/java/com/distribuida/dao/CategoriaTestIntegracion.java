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
//9
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class CategoriaTestIntegracion {

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Test
    public void findAll(){
        List<Categoria> categorias = categoriaDAO.findAll();
        categorias.forEach(System.out::println);
    }

    @Test
    public void findOne(){
        Optional<Categoria> categoria = categoriaDAO.findById(1);
        System.out.println(categoria.toString());
    }

    @Test
    public void save(){
        Categoria categoria = new Categoria();
        categoria.setNombre("Tecnología");
        categoriaDAO.save(categoria);
    }

    @Test
    public void update(){
        Optional<Categoria> categoria = categoriaDAO.findById(1);

        if(categoria.isPresent()){
            categoria.get().setNombre("Editada");
            categoriaDAO.save(categoria.get());
        } else {
            System.out.println("No existe categoría con ID 1");
        }
    }

    @Test
    public void delete(){
        categoriaDAO.deleteById(1);
    }
}

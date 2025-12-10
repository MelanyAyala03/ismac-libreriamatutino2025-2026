package com.distribuida.dao;

import com.distribuida.model.Autor;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class AutorRepositoryTestIntegracion {

    @Autowired
    private AutorDAO autorDAO;

    @Test
    public void findAll(){
        List<Autor> autores = autorDAO.findAll();
        assertNotNull(autores);
        autores.forEach(System.out::println);
    }

    @Test
    public void findOne(){
        Optional<Autor> autor = autorDAO.findById(1);
        System.out.println(autor.toString());
    }

    @Test
    public void save(){
        Autor autor = new Autor();
        autor.setNombre("Gabriel");
        autor.setNacionalidad("Colombiana");

        Autor guardado = autorDAO.save(autor);
        assertNotNull(guardado);
    }

    @Test
    public void update(){
        Autor autor = autorDAO.findById(4).orElseThrow();   // ← YA NO DEVUELVE NULL
        autor.setNombre("Autor Editado");
        autorDAO.save(autor);
    }

    @Test
    public void delete(){
        autorDAO.deleteById(1);
    }
}

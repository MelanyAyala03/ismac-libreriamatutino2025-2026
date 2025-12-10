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

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class AutorTestIntegracion {

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
    public void update() {

        // Cambiado a ID 2 para evitar conflicto con delete()
        Autor autor = autorDAO.findById(2)
                .orElseThrow(() -> new RuntimeException("Autor con ID 2 no existe"));

        autor.setNombre("Autor Editado");
        autorDAO.save(autor);

        Autor autorEditado = autorDAO.findById(2)
                .orElseThrow(() -> new RuntimeException("Autor con ID 2 no existe"));

        assertEquals("Autor Editado", autorEditado.getNombre());
    }

    @Test
    public void delete(){

        // Eliminamos ID 3 para NO eliminar el 1 ni 2 usados en otros tests
        autorDAO.deleteById(3);
    }
}
//7
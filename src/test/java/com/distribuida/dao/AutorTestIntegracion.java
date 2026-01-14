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
    public void testFindAll() {
        List<Autor> autores = autorDAO.findAll();
        assertNotNull(autores);
        assertTrue(autores.size() > 0);
        autores.forEach(System.out::println);
    }

    @Test
    public void testFindOne() {
        Optional<Autor> autorOpt = autorDAO.findById(1);
        assertTrue(autorOpt.isPresent());
        System.out.println(autorOpt.get());
    }

    @Test
    public void testSave() {
        Autor autor = new Autor(0,"Luis", "Mora", "Ecuador",
                "Av. Quito", "099000333", "correo@demo.com");

        Autor guardado = autorDAO.save(autor);
        assertNotNull(guardado);
        assertEquals("Luis", guardado.getNombre());
    }

    @Test
    public void Update() {
        Optional<Autor> autorOpt = autorDAO.findById(1);
        assertTrue(autorOpt.isPresent());

        autorOpt.get().setNombre("Carlos");
        autorOpt.get().setApellido("Vera");

        Autor actualizado = autorDAO.save(autorOpt.get());

        assertEquals("Carlos", actualizado.getNombre());
        assertEquals("Vera", actualizado.getApellido());
    }

    @Test
    public void Delete() {
        if (autorDAO.existsById(10)) {
            autorDAO.deleteById(10);
        }
        assertFalse(autorDAO.existsById(10));
    }
}

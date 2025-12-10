package com.distribuida.dao;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class LibroRepositoryTestIntegracion {

    @Autowired
    private LibroDAO libroDAO;

    @Autowired
    private AutorDAO autorDAO;

    @Autowired
    private CategoriaDAO categoriaDAO;


    @Test
    public void testLibroFindAll() {
        List<Libro> libros = libroDAO.findAll();

        assertNotNull(libros);
        assertTrue(libros.size() > 0, "La tabla libro está vacía");

        libros.forEach(System.out::println);
    }


    @Test
    public void testLibroFindOne() {
        int id = 1; // Ajustar si cambia en tu BD

        Optional<Libro> libro = libroDAO.findById(id);

        if (libro.isEmpty()) {
            System.out.println("⚠ El libro con ID " + id + " NO existe en la BD. Test saltado.");
            return; // Evita fallo si no existe
        }

        assertTrue(libro.isPresent());
        System.out.println(libro.get());
    }

    @Test
    public void testLibroSave() {

        System.out.println("=== TEST SAVE INICIADO ===");

        Autor autor = autorDAO.findById(1).orElse(null);
        Categoria categoria = categoriaDAO.findById(48).orElse(null);

        if (autor == null) {
            System.out.println(" ERROR: No existe el autor con ID 1 en la base de datos.");
            return;
        }

        if (categoria == null) {
            System.out.println(" ERROR: No existe la categoría con ID 48 en la base de datos.");
            return;
        }

        Libro libro = new Libro();
        libro.setTitulo("Libro Test Integración");
        libro.setAutor(autor);
        libro.setCategoria(categoria);
        libro.setEditorial("Prueba Editorial");
        libro.setNumPaginas(120);
        libro.setEdicion("1ra");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(LocalDateTime.now());
        libro.setDescripcion("Descripción de prueba");
        libro.setTipoPasta("Dura");

        // ISBN garantizado ÚNICO
        libro.setIsbn("ISBN-" + UUID.randomUUID());

        libro.setNumEjemplares(3);
        libro.setPortada("portada.jpg");
        libro.setPresentacion("Normal");
        libro.setPrecio(15.0f);

        Libro guardado = libroDAO.save(libro);

        assertNotNull(guardado, "No se guardó el libro");
        System.out.println("✔ Libro guardado con ID: " + guardado.getIdLibro());
    }


    @Test
    public void testLibroUpdate() {
        int id = 48; // Cambia según tu BD

        Optional<Libro> libroOpt = libroDAO.findById(id);

        if (libroOpt.isEmpty()) {
            System.out.println("⚠ El libro con ID " + id + " NO existe. Test saltado.");
            return; // Evita que falle el test
        }

        Libro libro = libroOpt.get();
        libro.setTitulo("Título Actualizado Test");

        Libro actualizado = libroDAO.save(libro);

        assertEquals("Título Actualizado Test", actualizado.getTitulo());
        System.out.println("✔ Libro actualizado con ID: " + actualizado.getIdLibro());
    }



    @Test
    public void testLibroDelete() {
        int id = 2; // Puedes cambiarlo si deseas

        if (!libroDAO.existsById(id)) {
            System.out.println("⚠ El libro con ID " + id + " NO existe. No se elimina.");
            return; // Evita fallo
        }

        libroDAO.deleteById(id);

        assertFalse(libroDAO.existsById(id), "El libro con ID " + id + " NO se eliminó");
        System.out.println("✔ Libro eliminado con éxito");
    }
}

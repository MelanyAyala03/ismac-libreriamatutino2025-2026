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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(false)
public class LibroTestIntegracion {

    @Autowired
    private LibroDAO libroDAO;

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Autowired
    private AutorDAO autorDAO;

    @Test
    public void testFindAll() {
        List<Libro> libros = libroDAO.findAll();
        assertNotNull(libros);
        assertTrue(libros.size() > 0);
        libros.forEach(System.out::println);
    }

    @Test
    public void testFindOne() {
        Optional<Libro> libroOpt = libroDAO.findById(1);
        assertTrue(libroOpt.isPresent());
        System.out.println(libroOpt.get());
    }

    @Test
    public void testSave() {
        Optional<Autor> autor = autorDAO.findById(3);
        Optional<Categoria> categoria = categoriaDAO.findById(2);

        assertTrue(autor.isPresent());
        assertTrue(categoria.isPresent());

        Libro libro = new Libro();
        libro.setIdLibro(0);
        libro.setTitulo("Nuevo Libro");
        libro.setEditorial("EditorialX");
        libro.setNumPaginas(150);
        libro.setEdicion("1ra");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(new Date());
        libro.setDescripcion("Descripción de prueba");
        libro.setTipoPasta("Dura");
        libro.setIsbn("978-84-376-0494-7");
        libro.setNumEjemplares(10);
        libro.setPortada("portada.png");
        libro.setPresentacion("Normal");
        libro.setPrecio(15);

        libro.setAutor(autor.orElse(null));
        libro.setCategoria(categoria.orElse(null));

        Libro Libroguardado = libroDAO.save(libro);

        assertNotNull(Libroguardado);
        assertEquals("Nuevo Libro", Libroguardado.getTitulo());
        assertEquals("EditorialX", Libroguardado.getEditorial());
        assertEquals("Español", Libroguardado.getIdioma());
        assertEquals("Matematica", Libroguardado.getCategoria());

    }

    @Test
    public void testUpdate() {
        Optional<Libro> libroOpt = libroDAO.findById(1);
        assertTrue(libroOpt.isPresent());

        libroOpt.get().setTitulo("Titulo Editado");

        Libro actualizado = libroDAO.save(libroOpt.get());
        assertEquals("Titulo Editado", actualizado.getTitulo());
    }

    @Test
    public void testDelete() {
        if (libroDAO.existsById(10)) {
            libroDAO.deleteById(10);
        }
        assertFalse(libroDAO.existsById(10));
    }
}

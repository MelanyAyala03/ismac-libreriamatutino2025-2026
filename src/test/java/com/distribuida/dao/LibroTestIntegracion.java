package com.distribuida.dao;

import com.distribuida.model.Libro;
import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class LibroTestIntegracion {
//8
    @Autowired
    private LibroDAO libroDAO;

    @Autowired
    private AutorDAO autorDAO;

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Test
    public void findAll(){
        List<Libro> libros = libroDAO.findAll();
        libros.forEach(System.out::println);
    }

    @Test
    public void findOne(){
        Optional<Libro> libro = libroDAO.findById(1);
        System.out.println(libro.orElse(null));
    }

    @Test
    public void save(){
        Autor autor = autorDAO.findById(1).orElse(null);
        Categoria categoria = categoriaDAO.findById(1).orElse(null);

        Libro libro = new Libro();
        libro.setTitulo("Libro de prueba");
        libro.setAutor(autor);
        libro.setCategoria(categoria);

        // CAMPOS NECESARIOS PARA QUE NO LANCE ERROR EN LA BD
        libro.setEditorial("Editorial prueba");
        libro.setNumPaginas(100);
        libro.setEdicion("Primera");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(LocalDateTime.now());
        libro.setDescripcion("Descripción prueba");
        libro.setTipoPasta("Dura");
        libro.setIsbn("1234567890");
        libro.setNumEjemplares(5);
        libro.setPortada("portada.jpg");
        libro.setPresentacion("Normal");
        libro.setPrecio(10.5f);

        libroDAO.save(libro);
    }

    @Test
    public void update(){
        Optional<Libro> libro = libroDAO.findById(1);

        if (libro.isPresent()) {
            libro.get().setTitulo("Título editado");
            libroDAO.save(libro.get());
        } else {
            System.out.println("El libro con ID 1 no existe.");
        }
    }

    @Test
    public void delete(){
        if (libroDAO.existsById(1)) {
            libroDAO.deleteById(1);
        } else {
            System.out.println("No se puede eliminar, el libro con ID 1 no existe.");
        }
    }
}

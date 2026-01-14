package com.distribuida.service;

import com.distribuida.Service.LibroServiceImpl;
import com.distribuida.dao.LibroDAO;
import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibroServiceTestUnitaria {

    @Mock
    private LibroDAO libroDAO;

    @InjectMocks
    private LibroServiceImpl libroService;

    private Libro libro;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategoria("Novela");

        Autor autor = new Autor();
        autor.setIdAutor(1);
        autor.setNombre("Autor Prueba");

        libro = new Libro(
                1,
                "Libro Test",
                "Editorial Test",
                200,
                "Primera",
                "Español",
                LocalDateTime.now(),
                "Descripción",
                "Dura",
                "ISBN123",
                10,
                "portada.jpg",
                "Física",
                25.5f,
                categoria,
                autor
        );
    }


    @Test
    void testFindAll() {
        when(libroDAO.findAll()).thenReturn(List.of(libro));

        List<Libro> resultado = libroService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(libroDAO, times(1)).findAll();
    }


    @Test
    void testFindOne() {
        when(libroDAO.findById(1)).thenReturn(Optional.of(libro));

        Optional<Libro> resultado = libroService.findOne(1);

        assertTrue(resultado.isPresent());
        assertEquals("Libro Test", resultado.get().getTitulo());
        verify(libroDAO, times(1)).findById(1);
    }


    @Test
    void testSave() {
        when(libroDAO.save(libro)).thenReturn(libro);

        Libro resultado = libroService.save(libro);

        assertNotNull(resultado);
        assertEquals("Libro Test", resultado.getTitulo());
        verify(libroDAO, times(1)).save(libro);
    }


    @Test
    void testUpdate() {
        when(libroDAO.findById(1)).thenReturn(Optional.of(libro));
        when(libroDAO.save(any(Libro.class))).thenReturn(libro);

        Libro actualizado = libroService.update(1, libro);

        assertNotNull(actualizado);
        verify(libroDAO, times(1)).findById(1);
        verify(libroDAO, times(1)).save(any(Libro.class));
    }


    @Test
    void testDelete() {
        when(libroDAO.existsById(1)).thenReturn(true);

        libroService.delete(1);

        verify(libroDAO, times(1)).existsById(1);
        verify(libroDAO, times(1)).deleteById(1);
    }
}

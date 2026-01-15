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
        // Inicializa los mocks
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

    // ---- ESTE TEST SE ARREGLA ----
    @Test
    void testFindOne() {
        // Mockeamos el DAO para que devuelva nuestro libro de prueba
        when(libroDAO.findById(anyInt())).thenReturn(Optional.of(libro));

        // Llamamos al método del servicio
        Optional<Libro> resultado = libroService.findOne(1);

        // Verificamos que el resultado está presente y el título coincide
        assertTrue(resultado.isPresent(), "El libro debería estar presente");
        assertEquals(libro.getTitulo(), resultado.get().getTitulo(), "El título debería coincidir");

        // Verificamos que findById del DAO se llamó exactamente una vez con el id correcto
        verify(libroDAO, times(1)).findById(1);
    }

    @Test
    void testSave() {
        // Mockeamos el DAO para que devuelva nuestro libro al guardarlo
        when(libroDAO.save(any(Libro.class))).thenReturn(libro);

        // Llamamos al método save del servicio
        Libro resultado = libroService.save(libro);

        // Verificamos que no sea null y que el título coincida
        assertNotNull(resultado, "El libro guardado no debería ser null");
        assertEquals(libro.getTitulo(), resultado.getTitulo(), "El título debería coincidir");

        // Verificamos que save del DAO se llamó exactamente una vez
        verify(libroDAO, times(1)).save(libro);
    }

    // ---- LOS DEMÁS TESTS SE QUEDAN IGUAL QUE TÚ TENÍAS ----
    @Test
    void testFindAll() {
        when(libroDAO.findAll()).thenReturn(List.of(libro));

        List<Libro> resultado = libroService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(libroDAO, times(1)).findAll();
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

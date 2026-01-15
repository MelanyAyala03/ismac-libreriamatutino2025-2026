package com.distribuida.service;

import com.distribuida.Service.AutorServiceImpl;
import com.distribuida.dao.AutorDAO;
import com.distribuida.model.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutorServiceTestUnitaria {

    @Mock
    private AutorDAO autorDAO;

    @InjectMocks
    private AutorServiceImpl autorService;

    private Autor autor;

    @BeforeEach
    void setUp() {
        autor = new Autor(
                1,
                "Gabriel",
                "García Márquez",
                "Colombia",
                "Aracataca",
                "0999999999",
                "gabriel@correo.com"
        );
    }

    @Test
    void findAll() {
        when(autorDAO.findAll()).thenReturn(List.of(autor));

        List<Autor> autores = autorService.findAll();

        assertNotNull(autores);
        assertEquals(1, autores.size());
        verify(autorDAO, times(1)).findAll();
    }

    @Test
    void findOneExistente() {
        when(autorDAO.findById(1)).thenReturn(Optional.of(autor));

        Optional<Autor> resultado = autorService.findOne(1);

        assertTrue(resultado.isPresent());
        assertEquals("Gabriel", resultado.get().getNombre());
    }

    @Test
    void findOneNoExistente() {
        when(autorDAO.findById(99)).thenReturn(Optional.empty());

        Optional<Autor> resultado = autorService.findOne(99);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void save() {
        when(autorDAO.save(autor)).thenReturn(autor);

        Autor resultado = autorService.save(autor);

        assertNotNull(resultado);
        assertEquals("García Márquez", resultado.getApellido());
    }

    @Test
    void updateExistente() {
        Autor autorActualizado = new Autor();
        autorActualizado.setNombre("Isabel");
        autorActualizado.setApellido("Allende");
        autorActualizado.setPais("Chile");
        autorActualizado.setDireccion("Santiago");
        autorActualizado.setTelefono("0988888888");
        autorActualizado.setCorreo("isabel@correo.com");

        when(autorDAO.findById(1)).thenReturn(Optional.of(autor));
        when(autorDAO.save(any(Autor.class))).thenReturn(autorActualizado);

        Autor resultado = autorService.update(1, autorActualizado);

        assertNotNull(resultado);
        assertEquals("Isabel", resultado.getNombre());
        verify(autorDAO, times(1)).save(autor);
    }

    @Test
    void updateNoExistente() {
        when(autorDAO.findById(999)).thenReturn(Optional.empty());

        Autor resultado = autorService.update(999, new Autor());

        assertNull(resultado);
        verify(autorDAO, never()).save(any());
    }

    @Test
    void deleteExistente() {
        when(autorDAO.existsById(1)).thenReturn(true);

        autorService.delete(1);

        verify(autorDAO).deleteById(1);
    }

    @Test
    void deleteNoExistente() {
        when(autorDAO.existsById(999)).thenReturn(false);

        autorService.delete(999);

        verify(autorDAO, never()).deleteById(anyInt());
    }
}
//
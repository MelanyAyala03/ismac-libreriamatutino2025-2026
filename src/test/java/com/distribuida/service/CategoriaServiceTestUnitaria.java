package com.distribuida.service;

import com.distribuida.Service.CategoriaServiceImpl;
import com.distribuida.dao.CategoriaDAO;
import com.distribuida.model.Categoria;
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
public class CategoriaServiceTestUnitaria {

    @Mock
    private CategoriaDAO categoriaDAO;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategoria("Novela");
        categoria.setDescripcion("Libros de ficción narrativa");
    }

    @Test
    public void findAll() {
        when(categoriaDAO.findAll()).thenReturn(List.of(categoria));

        List<Categoria> categorias = categoriaService.findAll();

        assertNotNull(categorias);
        assertEquals(1, categorias.size());
        verify(categoriaDAO, times(1)).findAll();
    }

    @Test
    public void findOneExistente() {
        when(categoriaDAO.findById(1)).thenReturn(Optional.ofNullable(categoria));

        Optional<Categoria> categoria1 = categoriaService.findOne(1);

        assertNotNull(categoria1);
        assertEquals("Novela", categoria1.orElse(null).getCategoria());
    }

    @Test
    public void findOneNoExistente() {
        when(categoriaDAO.findById(2)).thenReturn(null);

        Optional<Categoria> categoria = categoriaService.findOne(2);

        assertNull(categoria);
    }

    @Test
    public void save() {
        when(categoriaDAO.save(categoria)).thenReturn(categoria);

        Categoria categoria1 = categoriaService.save(categoria);

        assertNotNull(categoria1);
        assertEquals("Novela", categoria1.getCategoria());
    }

    @Test
    public void updateExistente() {
        Categoria categoriaActualizada = new Categoria();
        categoriaActualizada.setCategoria("Historia");
        categoriaActualizada.setDescripcion("Libros históricos");

        when(categoriaDAO.findById(1)).thenReturn(Optional.ofNullable(categoria));
        when(categoriaDAO.save(any())).thenReturn(categoriaActualizada);

        Categoria resultado = categoriaService.update(1, categoriaActualizada);

        assertNotNull(resultado);
        assertEquals("Historia", resultado.getCategoria());
        verify(categoriaDAO, times(1)).save(categoria);
    }

    @Test
    public void updateNoExistente() {
        Categoria categoriaNueva = new Categoria();

        when(categoriaDAO.findById(999)).thenReturn(Optional.empty());

        Categoria resultado = categoriaService.update(999, categoriaNueva);

        assertNull(resultado);
        verify(categoriaDAO, never()).save(any());
    }


    @Test
    public void testDeleteExistente() {
        when(categoriaDAO.existsById(1)).thenReturn(true);

        categoriaService.delete(1);

        verify(categoriaDAO).deleteById(1);
    }
//
    @Test
    public void testDeleteNoExistente() {
        when(categoriaDAO.existsById(999)).thenReturn(false);

        categoriaService.delete(999);

        verify(categoriaDAO, never()).deleteById(anyInt());
    }
}

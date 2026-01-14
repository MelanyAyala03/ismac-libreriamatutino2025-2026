package com.distribuida.service;

import com.distribuida.Service.ClienteService;
import com.distribuida.Service.ClienteServiceImpl;
import com.distribuida.dao.ClienteDAO;
import com.distribuida.model.cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTestUnitaria {

    @Mock
    private ClienteDAO clienteDAO;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private cliente Cliente;

    @BeforeEach
    public void setup(){
        Cliente = new cliente();
        Cliente.setIdCliente(1);
        Cliente.setCedula("1728437862");
        Cliente.setNombre("Melany");
        Cliente.setApellido("Ayala");
        Cliente.setDireccion("Av. tu casa");
        Cliente.setTelefono("0984605405");
        Cliente.setCorreo("melany@correo.com");
    }

    @Test
    public void findAll(){
        when(clienteDAO.findAll()).thenReturn(List.of(Cliente));
        List<cliente> clientes = clienteService.findAll();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        verify(clienteDAO, times(1)).findAll();
    }

    @Test
    public void testfindOne(){
        when(clienteDAO.findById(1)).thenReturn(Optional.ofNullable(Cliente));
        Optional<cliente> Cliente = clienteService.findOne(1);

        assertNotNull(Cliente);
        assertEquals("Melany", Cliente.orElse(null).getNombre());
    }

    @Test
    public void testFindOneNoExitente(){
        when(clienteDAO.findById(2)).thenReturn(null);
        Optional<cliente> Cliente = clienteService.findOne(2);
        assertNull(Cliente);
    }

    @Test
    public void testSave(){
        when(clienteDAO.save(Cliente)).thenReturn(Cliente);
        cliente clienteGuardado = clienteService.save(Cliente);

        assertNotNull(clienteGuardado);
        assertEquals("Melany", clienteGuardado.getNombre());
    }

    @Test
    public void testUpdateExistente(){
        cliente clienteActualizado = new cliente();
        clienteActualizado.setCedula("1713690400");
        clienteActualizado.setNombre("Monica");
        clienteActualizado.setApellido("Patricia");
        clienteActualizado.setDireccion("su casa");
        clienteActualizado.setTelefono("0983111862");
        clienteActualizado.setCorreo("Monica@correo.com");

        when(clienteDAO.findById(1)).thenReturn(Optional.ofNullable(Cliente));
        when(clienteDAO.save(any())).thenReturn(clienteActualizado);

        cliente clienteResultado = clienteService.update(1, clienteActualizado);

        assertNotNull(clienteResultado);
        assertEquals("Monica", clienteResultado.getNombre());
        verify(clienteDAO, times(1)).save(Cliente);
    }

    @Test
    public void testUpdateNoExistente(){
        cliente clienteNuevo = new cliente();
        when(clienteDAO.findById(999)).thenReturn(null);
        cliente resultado = clienteService.update(999, clienteNuevo);

        assertNull(resultado);
        verify(clienteDAO, never()).save(any());
    }

    @Test
    public void testDeleteExistente(){
        when(clienteDAO.existsById(1)).thenReturn(true);
        clienteService.delete(1);
        verify(clienteDAO).deleteById(1);
    }

    @Test
    public void testDeleteNoExistente(){
        when(clienteDAO.existsById(999)).thenReturn(false);
        clienteService.delete(999);
        verify(clienteDAO, never()).deleteById(anyInt());
    }

}

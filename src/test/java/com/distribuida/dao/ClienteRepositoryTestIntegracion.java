package com.distribuida.dao;

import com.distribuida.model.Cliente;
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
public class ClienteRepositoryTestIntegracion {

    @Autowired
    public ClienteDAO clienteDAO;

    // 1. LISTAR TODOS
    @Test
    public void testClientefindAlll() {
        List<Cliente> clientes = clienteDAO.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);

        clientes.forEach(System.out::println);
    }

    // 2. BUSCAR UNO POR ID
    @Test
    public void testClientefindOne() {
        Optional<Cliente> cliente = clienteDAO.findById(1);

        assertTrue(cliente.isPresent(), "El cliente debería existir");
        assertEquals("Puro", cliente.get().getNombre());
        assertEquals("Hueso", cliente.get().getApellido());

        System.out.println(cliente);
    }

    // 3. GUARDAR CLIENTE (CORREGIDO)
    @Test
    public void save() {
        Cliente cliente = new Cliente(
                "170123456",
                0,
                "Juan6",
                "Taipe6",
                "Av via sapo",
                "0987456321",
                "Jtaipe@correo.com"
        );

        Cliente clienteGuardado = clienteDAO.save(cliente);

        assertNotNull(clienteGuardado, "El cliente se ha guardado CORRECTAMENTE");
        assertEquals("170123456", clienteGuardado.getCedula());
        assertEquals("Juan6", clienteGuardado.getNombre());  // corregido
    }

    // 4. ACTUALIZAR CLIENTE (CORREGIDO)
    @Test
    public void testClienteActualizar() {

        Optional<Cliente> cliente = clienteDAO.findById(39);

        assertTrue(cliente.isPresent(), "El cliente existe en BD");

        cliente.get().setCedula("17012345677");
        cliente.get().setNombre("Juan77");
        cliente.get().setApellido("Taipe77");
        cliente.get().setDireccion("Direccion77");
        cliente.get().setTelefono("09876543277");
        cliente.get().setCorreo("Jtaipe77@correo.com");

        Cliente clienteActualizado = clienteDAO.save(cliente.get());

        assertNotNull(clienteActualizado, "El cliente fue actualizado");
        assertEquals("Juan77", clienteActualizado.getNombre());
        assertEquals("Direccion77", clienteActualizado.getDireccion()); // corregido
    }

    // 5. ELIMINAR CLIENTE
    @Test
    public void testClienteBorrar() {
        if (clienteDAO.existsById(40)) {
            clienteDAO.deleteById(40);
        }
        assertFalse(clienteDAO.existsById(40), "El dato fue eliminado");
    }
}

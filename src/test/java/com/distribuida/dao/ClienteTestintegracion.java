package com.distribuida.dao;

import com.distribuida.model.cliente;
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
public class ClienteTestintegracion {

    @Autowired
    public ClienteDAO clienteDAO;

    @Test
    public void findAlll(){
        List<cliente> clientes =clienteDAO.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
        for (cliente item: clientes){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<cliente> Cliente = clienteDAO.findById(1);
        assertTrue(Cliente.isPresent(), "Elclinte con id=1 si existe");
        System.out.println(Cliente.toString());
    }

    @Test
    public void save(){
        cliente cliente = new cliente("170123456", 0, "Juan6", "Taipe6", "Av.queti y sapo", "0987456321","Jtaipe@correo.com");
        cliente clienteGuardado = clienteDAO.save(cliente);
        assertNotNull(clienteGuardado, "El cliente nuevo se guardo correctamente");
        assertEquals("170123456", clienteGuardado.getCedula());
        assertEquals("Juan6",clienteGuardado.getNombre());
    }

    @Test
    public void update(){

        Optional<cliente> Cliente = clienteDAO.findById(40);

        assertTrue(Cliente.isPresent(), "El cliente existe en BD");

        Cliente.orElse(null).setCedula("17012345677");
        Cliente.orElse(null).setNombre("Juan77");
        Cliente.orElse(null).setApellido("Taipe77");
        Cliente.orElse(null).setDireccion("Direccion77");
        Cliente.orElse(null).setTelefono("09876543277");
        Cliente.orElse(null).setCorreo("Jtaipe77@correo.com");

        cliente clienteActualizado = clienteDAO.save(Cliente.orElse(null));
        assertNotNull(clienteActualizado, "El cliente fue actualizado");
        assertEquals("Juan77", clienteActualizado.getNombre());
        assertEquals("Taipe77", clienteActualizado.getApellido());

    }

    @Test
    public void delete(){
        if(clienteDAO.existsById(40)) {
            clienteDAO.deleteById(40);
        }
        assertFalse(clienteDAO.existsById(40), "El dato fue eliminado");
    }

}

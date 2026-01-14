package com.distribuida.Service;

import com.distribuida.dao.ClienteDAO;
import com.distribuida.model.cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public List<cliente> findAll() {
        return clienteDAO.findAll();
    }

    @Override
    public Optional<cliente> findOne(int id) {
        return clienteDAO.findById(id);
    }

    @Override
    public cliente save(cliente Cliente) {
        return clienteDAO.save(Cliente);
    }

    @Override
    public cliente update(int id, cliente Cliente) {

        Optional<cliente> clienteExiste = clienteDAO.findById(id);

        if (clienteExiste == null){
            return null;
        }

        clienteExiste.orElse(null).setCedula(Cliente.getCedula());
        clienteExiste.orElse(null).setNombre(Cliente.getNombre());
        clienteExiste.orElse(null).setApellido(Cliente.getApellido());
        clienteExiste.orElse(null).setDireccion(Cliente.getDireccion());
        clienteExiste.orElse(null).setTelefono(Cliente.getTelefono());
        clienteExiste.orElse(null).setCorreo(Cliente.getCorreo());


        return clienteDAO.save(clienteExiste.orElse(null));
    }

    @Override
    public void delete(int id) {

        if (clienteDAO.existsById(id)){
            clienteDAO.deleteById(id);
        }

    }

}

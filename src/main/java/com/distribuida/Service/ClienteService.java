package com.distribuida.Service;

import com.distribuida.model.cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    public List<cliente> findAll();
    public Optional<cliente> findOne(int id);
    public cliente save(cliente Cliente);
    public cliente update(int id, cliente Cliente);
    public void delete(int id);

}

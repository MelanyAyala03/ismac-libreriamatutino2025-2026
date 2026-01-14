package com.distribuida.Service;

import com.distribuida.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> findAll();
    Optional<Categoria> findOne(int id);
    Categoria save(Categoria c);
    Categoria update(int id, Categoria c);
    void delete(int id);
}

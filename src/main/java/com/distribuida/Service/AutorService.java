package com.distribuida.Service;

import com.distribuida.model.Autor;
import java.util.List;
import java.util.Optional;

public interface AutorService {

    List<Autor> findAll();
    Optional<Autor> findOne(int id);
    Autor save(Autor a);
    Autor update(int id, Autor a);
    void delete(int id);
}

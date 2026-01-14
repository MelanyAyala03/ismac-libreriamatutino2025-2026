package com.distribuida.Service;

import com.distribuida.model.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<Libro> findAll();
    Optional<Libro> findOne(int id);
    Libro save(Libro l);
    Libro update(int id, Libro l);
    void delete(int id);


}

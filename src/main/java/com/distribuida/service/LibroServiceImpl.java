package com.distribuida.service;

import com.distribuida.model.Libro;

import java.util.List;

public class LibroServiceImpl implements LibroService{


    @Override
    public List<Libro> findAll() {
        return List.of();
    }

    @Override
    public Libro findOne(int id){
        return null;
    }
//
    @Override
    public Libro save(Libro libro) {
        return null;
    }

    @Override
    public Libro update(Libro libro) {
        return null;
    }

    @Override
    public void delete(int id){

}


}
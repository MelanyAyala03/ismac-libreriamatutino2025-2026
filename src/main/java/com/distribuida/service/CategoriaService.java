package com.distribuida.service;

import com.distribuida.model.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> findAll();

    Categoria findOne();

    Categoria findOne(int id);

    Categoria save();

    Categoria update();

    Categoria save(Categoria categoria);

    Categoria update(Categoria categoria);
//
    void delete(int id);
}
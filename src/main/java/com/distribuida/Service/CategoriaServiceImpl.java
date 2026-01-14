package com.distribuida.Service;

import com.distribuida.dao.CategoriaDAO;
import com.distribuida.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Override
    public List<Categoria> findAll() {
        return categoriaDAO.findAll();
    }

    @Override
    public Optional<Categoria> findOne(int id) {
        return categoriaDAO.findById(id);
    }

    @Override
    public Categoria save(Categoria c) {
        return categoriaDAO.save(c);
    }

    @Override
    public Categoria update(int id, Categoria c) {

        Optional<Categoria> cExiste = categoriaDAO.findById(id);

        if (cExiste.isEmpty()) {
            return null;
        }

        Categoria cat = cExiste.get();
        cat.setCategoria(c.getCategoria());
        cat.setDescripcion(c.getDescripcion());

        return categoriaDAO.save(cat);
    }

    @Override
    public void delete(int id) {
        if (categoriaDAO.existsById(id)) {
            categoriaDAO.deleteById(id);
        }
    }
}
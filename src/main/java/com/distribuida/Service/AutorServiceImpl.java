package com.distribuida.Service;

import com.distribuida.dao.AutorDAO;
import com.distribuida.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorDAO autorDAO;

    @Override
    public List<Autor> findAll() {
        return autorDAO.findAll();
    }

    @Override
    public Optional<Autor> findOne(int id) {
        return autorDAO.findById(id);
    }

    @Override
    public Autor save(Autor a) {
        return autorDAO.save(a);
    }

    @Override
    public Autor update(int id, Autor a) {

        Optional<Autor> autorExiste = autorDAO.findById(id);

        if (autorExiste.isEmpty()) {
            return null;
        }

        Autor au = autorExiste.get();
        au.setNombre(a.getNombre());
        au.setApellido(a.getApellido());
        au.setPais(a.getPais());
        au.setDireccion(a.getDireccion());
        au.setTelefono(a.getTelefono());
        au.setCorreo(a.getCorreo());

        return autorDAO.save(au);
    }

    @Override
    public void delete(int id) {
        if (autorDAO.existsById(id)) {
            autorDAO.deleteById(id);
        }
    }
}
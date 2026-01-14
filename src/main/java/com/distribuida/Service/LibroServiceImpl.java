package com.distribuida.Service;

import com.distribuida.dao.LibroDAO;
import com.distribuida.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroDAO libroDAO;

    @Override
    public List<Libro> findAll() {
        return libroDAO.findAll();
    }

    @Override
    public Optional<Libro> findOne(int id) {
        return libroDAO.findById(id);
    }

    @Override
    public Libro save(Libro l) {
        return libroDAO.save(l);
    }

    @Override
    public Libro update(int id, Libro l) {

        Optional<Libro> libroExiste = libroDAO.findById(id);

        if (libroExiste.isEmpty()) {
            return null;
        }

        Libro lib = libroExiste.get();
        lib.setTitulo(l.getTitulo());
        lib.setEditorial(l.getEditorial());
        lib.setNumPaginas(l.getNumPaginas());
        lib.setEdicion(l.getEdicion());
        lib.setIdioma(l.getIdioma());
        lib.setFechaPublicacion(l.getFechaPublicacion());
        lib.setDescripcion(l.getDescripcion());
        lib.setTipoPasta(l.getTipoPasta());
        lib.setIsbn(l.getIsbn());
        lib.setNumEjemplares(l.getNumEjemplares());
        lib.setPortada(l.getPortada());
        lib.setPresentacion(l.getPresentacion());
        lib.setPrecio(l.getPrecio());

        lib.setCategoria(l.getCategoria());
        lib.setAutor(l.getAutor());

        return libroDAO.save(lib);
    }

    @Override
    public void delete(int id) {
        if (libroDAO.existsById(id)) {
            libroDAO.deleteById(id);
        }
    }
}
package com.distribuida.controller;

import com.distribuida.Service.LibroService;
import com.distribuida.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
//
    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> findAll(){ return  ResponseEntity.ok(libroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> findOne(@PathVariable int id){
        Optional<Libro> libro = libroService.findOne(id);

        if (libro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro.orElse(null));
    }

    @PostMapping
    public ResponseEntity<Libro> save(@RequestBody Libro libro){
        return ResponseEntity.ok(libroService.save(libro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> update(@PathVariable int id, @RequestBody Libro libro){
        Libro libroActualizado = libroService.update(id, libro);

        if (libroActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libroActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

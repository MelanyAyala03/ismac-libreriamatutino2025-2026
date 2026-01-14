package com.distribuida.controller;

import com.distribuida.Service.ClienteService;
import com.distribuida.model.cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")

public class ClienteController {
//8
    @Autowired
    private ClienteService clienteservice;

    @GetMapping
    public ResponseEntity<List<cliente>> findAll(){
        List<cliente> clientes = clienteservice.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<cliente> findOne(@PathVariable int id){
        Optional<cliente> Cliente = clienteservice.findOne(id);
        if (Cliente == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Cliente.orElse(null));
    }

    @PostMapping
    public ResponseEntity<cliente> save(@RequestBody cliente Cliente){
        cliente nuevoCliente = clienteservice.save(Cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<cliente> update(@PathVariable int id, @RequestBody cliente Cliente){
        cliente clienteActualizado = clienteservice.update(id, Cliente);
        if (clienteActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        clienteservice.delete(id);
        return ResponseEntity.noContent().build();
    }

}

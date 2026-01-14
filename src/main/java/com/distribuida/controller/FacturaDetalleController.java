package com.distribuida.controller;

import com.distribuida.Service.FacturaDetalleService;
import com.distribuida.model.FacturaDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturadetalles")
public class FacturaDetalleController {
//
    @Autowired
    private FacturaDetalleService facturaDetalleService;

    @GetMapping
    public ResponseEntity<List<FacturaDetalle>> findAll(){return ResponseEntity.ok(facturaDetalleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDetalle> findOne(@PathVariable int id){
        Optional<FacturaDetalle> facturaDetalle = facturaDetalleService.findOne(id);

        if (facturaDetalle == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturaDetalle.orElse(null));
    }

    @PostMapping
    public ResponseEntity<FacturaDetalle> save(@RequestBody FacturaDetalle facturaDetalle){
        return ResponseEntity.ok(facturaDetalleService.save(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDetalle> update(@PathVariable int id, @RequestBody FacturaDetalle facturaDetalle){
        FacturaDetalle FacturaDetalleActualizado = facturaDetalleService.update(id, facturaDetalle);

        if (FacturaDetalleActualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(FacturaDetalleActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        facturaDetalleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

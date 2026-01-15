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

    @Autowired
    private FacturaDetalleService facturaDetalleService;

    @GetMapping
    public ResponseEntity<List<FacturaDetalle>> findAll() {
        return ResponseEntity.ok(facturaDetalleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDetalle> findOne(@PathVariable int id) {
        Optional<FacturaDetalle> facturaDetalle = facturaDetalleService.findOne(id);

        if (facturaDetalle.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(facturaDetalle.get());
    }

    @PostMapping
    public ResponseEntity<FacturaDetalle> save(@RequestBody FacturaDetalle facturaDetalle) {
        return ResponseEntity.ok(facturaDetalleService.save(facturaDetalle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDetalle> update(
            @PathVariable int id,
            @RequestBody FacturaDetalle facturaDetalle) {

        FacturaDetalle facturaDetalleActualizado =
                facturaDetalleService.update(id, facturaDetalle);

        if (facturaDetalleActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(facturaDetalleActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        facturaDetalleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

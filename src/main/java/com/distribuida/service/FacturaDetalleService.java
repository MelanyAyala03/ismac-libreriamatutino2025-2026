package com.distribuida.service;

import com.distribuida.model.FacturaDetalle;

import java.util.List;

public interface FacturaDetalleService {
    List<FacturaDetalle> findAll();

    FacturaDetalle findOne(
            int id);
//
    FacturaDetalle save(FacturaDetalle facturaDetalle);

    FacturaDetalle update(FacturaDetalle facturaDetalle);

    void delete(int id);
}
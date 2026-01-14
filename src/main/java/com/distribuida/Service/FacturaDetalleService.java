package com.distribuida.Service;

import com.distribuida.model.FacturaDetalle;
import java.util.List;
import java.util.Optional;

public interface FacturaDetalleService {

    List<FacturaDetalle> findAll();
    Optional<FacturaDetalle> findOne(int id);
    FacturaDetalle save(FacturaDetalle fd);
    FacturaDetalle update(int id, FacturaDetalle fd);
    void delete(int id);
}

package com.distribuida.dao;

import com.distribuida.model.FacturaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaDetalleDAO extends JpaRepository<FacturaDetalle, Integer> {
}
//8
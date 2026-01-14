package com.distribuida.Service;

import com.distribuida.dao.FacturaDetalleDAO;
import com.distribuida.model.FacturaDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaDetalleServiceImpl implements FacturaDetalleService {

    @Autowired
    private FacturaDetalleDAO facturaDetalleDAO;

    @Override
    public List<FacturaDetalle> findAll() {
        return facturaDetalleDAO.findAll();
    }

    @Override
    public Optional<FacturaDetalle> findOne(int id) {
        return facturaDetalleDAO.findById(id);
    }

    @Override
    public FacturaDetalle save(FacturaDetalle fd) {
        return facturaDetalleDAO.save(fd);
    }

    @Override
    public FacturaDetalle update(int id, FacturaDetalle fd) {

        Optional<FacturaDetalle> fdExiste = facturaDetalleDAO.findById(id);

        if (fdExiste.isEmpty()) {
            return null;
        }

        FacturaDetalle detalle = fdExiste.get();
        detalle.setCantidad(fd.getCantidad());
        detalle.setSubtotal(fd.getSubtotal());
        detalle.setFactura(fd.getFactura());
        detalle.setLibro(fd.getLibro());

        return facturaDetalleDAO.save(detalle);
    }

    @Override
    public void delete(int id) {
        if (facturaDetalleDAO.existsById(id)) {
            facturaDetalleDAO.deleteById(id);
        }
    }
}

package com.distribuida.Service;

import com.distribuida.dao.FacturaDAO;
import com.distribuida.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaDAO facturaDAO;

    @Override
    public List<Factura> findAll() {
        return facturaDAO.findAll();
    }

    @Override
    public Optional<Factura> findOne(int id) {
        return facturaDAO.findById(id);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaDAO.save(factura);
    }

    @Override
    public Factura update(int id, Factura f) {

        Optional<Factura> optionalFactura = facturaDAO.findById(id);


        if (optionalFactura.isEmpty()) {
            return null;
        }

        Factura factura = optionalFactura.get();

        if (f == null) {
            return null;
        }

        factura.setNumFactura(f.getNumFactura());
        factura.setFecha(f.getFecha());
        factura.setTotalNeto(f.getTotalNeto());
        factura.setIva(f.getIva());
        factura.setTotal(f.getTotal());

        if (f.getCliente() != null) {
            factura.setCliente(f.getCliente());
        }

        return facturaDAO.save(factura);
    }

    @Override
    public void delete(int id) {
        if (facturaDAO.existsById(id)) {
            facturaDAO.deleteById(id);
        }
    }
}

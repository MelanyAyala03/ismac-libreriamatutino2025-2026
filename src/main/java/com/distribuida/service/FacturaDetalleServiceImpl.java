package com.distribuida.service;

import com.distribuida.model.FacturaDetalle;

import java.util.List;

public class FacturaDetalleServiceImpl  implements  FacturaDetalleService{

    @Override
    public List<FacturaDetalle> findAll(){
        return  List.of();
    }


    @Override
    public FacturaDetalle findOne(int id){
        return null;
    }

    @Override
    public FacturaDetalle save(FacturaDetalle facturaDetalle){
        return null;
    }
//
    @Override
    public FacturaDetalle update(FacturaDetalle facturaDetalle){
        return null;
    }

    @Override
    public void delete(int id){

}

}
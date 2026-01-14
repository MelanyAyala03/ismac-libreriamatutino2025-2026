package com.distribuida.dao;

import com.distribuida.model.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@repository esta anotacion es para hacer la clase bean
public interface ClienteDAO extends JpaRepository<cliente, Integer> {

    //cliente findByNombreAndApellido(int id);

}

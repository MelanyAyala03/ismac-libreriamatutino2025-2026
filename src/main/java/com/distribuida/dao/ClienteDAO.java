package com.distribuida.dao;

import com.distribuida.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@repository esta anotacion es para hacer la clase bean
public interface ClienteDAO extends JpaRepository<Cliente, Integer> {
//8
    //cliente findByNombreAndApellido(int id);

}

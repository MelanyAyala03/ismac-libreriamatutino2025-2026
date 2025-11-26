package com.distribuida.principal;

import com.distribuida.model.Cliente;

public class ClientePrincipal {

    public static void main(String[] args){

        Cliente cliente = new Cliente("1726813684", 1, "Mateo", "Benitez", "AV. queti y sapo", "0997615005", "mateo78_ricardo@hotmail.com");

        System.out.println(cliente.toString());

    }

}

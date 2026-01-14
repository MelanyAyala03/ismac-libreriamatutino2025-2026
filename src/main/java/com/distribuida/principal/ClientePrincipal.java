package com.distribuida.principal;

import com.distribuida.model.cliente;

public class ClientePrincipal {

    public static void main(String[] args){

        cliente cliente = new cliente("1726813684", 1, "Mateo", "Benitez", "AV. queti y sapo", "0997615005", "mateo78_ricardo@hotmail.com");

        System.out.println(cliente.toString());

    }

}

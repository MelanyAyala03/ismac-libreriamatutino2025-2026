package com.distribuida.principal;

import com.distribuida.model.Autor;

public class AutorPrincipal {
    public static void main(String[] args) {
        Autor autor = new Autor(1, "Gabriel", "García Márquez", "Colombia", "Calle 123", "0991234567", "gabriel@gmail.com");
        System.out.println(autor.toString());
    }
}
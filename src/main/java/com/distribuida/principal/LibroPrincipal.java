package com.distribuida.principal;

import com.distribuida.model.*;

import java.util.Date;

public class LibroPrincipal {
    public static void main(String[] args) {


        Libro libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitulo("Cien años de soldad");
        libro.setEditorial("");
        libro.setNumPaginas(471);
        libro.setEdicion("1ra");
        libro.setIdioma("Español");
        libro.setFechaPublicacion(new Date());
        libro.setDescripcion("Novela Historica");
        libro.setTipoPasta("Dura");
        libro.setIsbn("978-84-376-0494-7");
        libro.setNumEjemplares(50);
        libro.setPortada("portada.jpg");
        libro.setPresentacion("Tapa dura con ilustraciones");
        libro.setPrecio(20);

        Categoria categoria = new Categoria(1, "Novela", "Libros de ficción");
        Autor autor = new Autor(1, "Gabriel", "García Márquez", "Colombia", "Calle 123", "0991234567", "gabriel@gmail.com");
        System.out.println(libro.toString());
    }
}
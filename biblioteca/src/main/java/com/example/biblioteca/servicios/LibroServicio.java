/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.biblioteca.servicios;

import com.example.biblioteca.exepciones.MiException;
import com.example.biblioteca.entidades.Autor;
import com.example.biblioteca.entidades.Editorial;
import com.example.biblioteca.entidades.Libro;
import com.example.biblioteca.repositorio.AutorRepositorio;
import com.example.biblioteca.repositorio.EditorialRepositorio;
import com.example.biblioteca.repositorio.LibroRepositorio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aguir
 */
@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {

        validar(isbn,titulo,ejemplares,idAutor,idEditorial);

        Libro libro = new Libro();
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(LocalDate.now());

        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);

    }

    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList();

        libros = libroRepositorio.findAll();

        return libros;

    }

    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {
        
        validar(isbn,titulo,ejemplares,idAutor,idEditorial);

        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {

            autor = respuestaAutor.get();

        }
        if (respuestaEditorial.isPresent()) {

            editorial = respuestaEditorial.get();

        }

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);

            libroRepositorio.save(libro);
        }

    }

   

    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException{
        if (isbn == null) {
            throw new MiException("El ISBN NO PUEDE SER NULL");
        }
        if (titulo.isEmpty() || titulo == null) {

            throw new MiException("El TITULO NO PUEDE ESTAR VACIO O SER NULL");

        }

        if (ejemplares == null) {
            throw new MiException("LOS EJEMPLARES NO PUEDEN SER NULL");
        }

        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("El AUTOR ID NO PUEDE ESTAR VACIO O SER NULL");
        }

        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("LA EDITORIAL ID NO PUEDE ESTAR VACIO O SER NULL");
        }
        
    }

}

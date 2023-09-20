/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.biblioteca.controladores;

import com.example.biblioteca.exepciones.MiException;
import com.example.biblioteca.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author aguir
 */

    @Controller
@RequestMapping("/editorial")
public class EditorialControlador {//localhost:8080/editorial

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") //localhost:8080/editorial/registar
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro") //localhost:8080/editorial/registro
    public String registro(@RequestParam String nombreEditorial, ModelMap modelo) {

        try {
            editorialServicio.crearEditorial(nombreEditorial);
            modelo.put("exito", "La Editorial fue cargada exitosamente");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            //Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "editorial_form.html";
        }
        return "index.html";
    }

}


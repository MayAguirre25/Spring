/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.biblioteca.controladores;

import com.example.biblioteca.exepciones.MiException;
import com.example.biblioteca.servicios.AutorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/autor") //localhost:8080/autor
public class AutorControlador {    
    
    @Autowired
    private AutorServicio autorServicio;
    
    @GetMapping("/registrar") //loccalhost:8080/autor/registar
    public String registrar(){
        return "autor_form.html";
    }
    
    @PostMapping("/registro") //loccalhost:8080/autor/registro
    public String registro(@RequestParam String nombre, ModelMap modelo){
        try{
            autorServicio.crearAutor(nombre);
            modelo.put("exito", "El Autor fue cargado exitosamente");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            //Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor_form.html";
        }
        
        return "index.html";
    }

}
    
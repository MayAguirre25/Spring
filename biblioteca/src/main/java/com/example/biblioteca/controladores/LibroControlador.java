/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.biblioteca.controladores;

import com.example.biblioteca.entidades.Autor;
import com.example.biblioteca.entidades.Editorial;
import com.example.biblioteca.exepciones.MiException;
import com.example.biblioteca.servicios.AutorServicio;
import com.example.biblioteca.servicios.EditorialServicio;
import com.example.biblioteca.servicios.LibroServicio;
import java.util.List;
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
@RequestMapping("/libro")
public class LibroControlador {//localhost:8080/libro

    @Autowired
    private EditorialServicio editorialServicio;
    
    @Autowired
    private AutorServicio autorServicio;
    
    @Autowired
    private LibroServicio libroServicio;

    @GetMapping("/registrar") //localhost:8080/libro/registar
    public String registrar(ModelMap modelo) {//El modelMap nos muestra por pantalla
        
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        
        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        
        return "libro_form.html";
    }
    @PostMapping("/registro") //localhost:8080/libro/registro
    public String registro(@RequestParam(required=false) Long isbn, @RequestParam String titulo, @RequestParam(required=false) Integer ejemplares, 
            @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo) {//se considera nulo para el caso que el isbn sea nulo y que esto se trate en el servicio con la exception

        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El libro fue cargado exitosamente");//
            
        } catch (MiException ex) {
           modelo.put("error", ex.getMessage());//modelo nos muestra el mensaje por error por pantalla
            //Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE,null,ex);
           return "libro_form.html";
        }
        return "index.html";
    }

}

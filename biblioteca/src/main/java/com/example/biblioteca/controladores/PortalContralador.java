/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author aguir
 */
@Controller
public class PortalContralador {
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
}

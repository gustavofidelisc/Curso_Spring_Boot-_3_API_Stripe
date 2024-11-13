package org.example.aplicationstripe.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/*
* Controlador cuida das requisições
* Service logica de negocios
* repository persiste os dados
* */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}


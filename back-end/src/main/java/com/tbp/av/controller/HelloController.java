package com.tbp.av.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // TODO este controller nao tem sentido existir

    @RequestMapping("/")
    public String index() {
        return "It is working!";
    }

}

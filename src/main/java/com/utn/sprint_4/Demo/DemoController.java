package com.utn.sprint_4.Demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {

    @PostMapping(value = "demoUser")
    public String welcome()
    {
        return "Hola, te has autenticado con exito";
    }

    @PostMapping(value = "demoAdmin")
    public String welcomeAdmin()
    {
        return "Hola, te has autenticado con exito como Administrador";
    }

}
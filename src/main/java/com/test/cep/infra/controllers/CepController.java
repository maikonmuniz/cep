package com.test.cep.infra.controllers;

import com.test.cep.services.CepServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CepController {
    @Autowired
    private CepServices cepServices;

    @GetMapping("/{cep}")
    public String consultCep(@PathVariable String cep) {
        String result = this.cepServices.execute(cep);
        return result;
    }
}

package com.test.cep.infra.controllers;

import com.test.cep.services.CepServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/cep")
public class CepController {
    @Autowired
    private CepServices cepServices;

    @GetMapping("/{cep}")
    public Object consultCep(@PathVariable String cep) {
        Object result = this.cepServices.execute(cep);
        return result;
    }
}

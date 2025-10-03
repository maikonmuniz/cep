package com.test.cep.services;

import com.test.cep.infra.exception.ExceptionAdapter;
import com.test.cep.infra.http.ApiAdapter;

public class CepServices {

    private final ApiAdapter apiAdapter;
    private final ExceptionAdapter exceptionAdapter;

    public CepServices (ApiAdapter apiAdapter, ExceptionAdapter exceptionAdapter) {
        this.apiAdapter = apiAdapter;
        this.exceptionAdapter = exceptionAdapter;
    }

    public Object execute (String cep) {
        if (cep == null || cep.isEmpty()) throw this.exceptionAdapter.badRequest("Campo cep está em branco!");
        if (!cep.matches("\\d+")) throw this.exceptionAdapter.badRequest("Campo cep deve conter apenas números!");
        return this.apiAdapter.Get(cep);
    }
}

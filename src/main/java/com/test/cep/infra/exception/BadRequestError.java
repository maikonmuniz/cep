package com.test.cep.infra.exception.implementation;

public class BadRequestError extends RuntimeException {
    public BadRequestError(String mensagem) {
        super(mensagem);
    }
}

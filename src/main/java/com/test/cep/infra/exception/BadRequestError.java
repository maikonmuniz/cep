package com.test.cep.infra.exception;

public class BadRequestError extends RuntimeException {
    public BadRequestError(String mensagem) {
        super(mensagem);
    }
}

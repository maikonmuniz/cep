package com.test.cep.infra.exception;

public class OperationStatusCode {
    public RuntimeException BadRequesteError (String message) {
        throw new BadRequestError (message);
    }
}

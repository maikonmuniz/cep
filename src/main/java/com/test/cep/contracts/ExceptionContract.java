package com.test.cep.contracts;

public interface ExceptionContract {
    RuntimeException badRequest(String mess);
    RuntimeException internalServe(String mess);
}

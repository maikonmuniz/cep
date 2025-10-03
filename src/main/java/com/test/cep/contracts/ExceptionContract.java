package com.test.cep.contracts;

public interface ExceptionInterContract {
    RuntimeException badRequest(String mess);
    RuntimeException internalServe(String mess);
}

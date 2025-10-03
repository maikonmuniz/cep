package com.test.cep.main;

import com.test.cep.infra.exception.ExceptionAdapter;
import com.test.cep.infra.exception.OperationStatusCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainException {
    @Bean
    public OperationStatusCode CreateOperationStatusCode () {
        return new OperationStatusCode ();
    }

    @Bean
    public ExceptionAdapter createExceptionAdapter (
            OperationStatusCode operationStatusCode
    ) {
        return new ExceptionAdapter(operationStatusCode);
    }
}

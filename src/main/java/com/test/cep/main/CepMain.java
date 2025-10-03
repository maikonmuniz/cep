package com.test.cep.main;

import com.test.cep.infra.exception.ExceptionAdapter;
import com.test.cep.infra.http.ApiAdapter;
import com.test.cep.services.CepServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CepMain {

    @Bean
    ApiAdapter createApiAdapter (RestTemplate api) {
        return new ApiAdapter(api);
    }

    @Bean
    CepServices createCepServices (ApiAdapter apiAdapter, ExceptionAdapter exceptionAdapter) {
        return new CepServices(apiAdapter, exceptionAdapter);
    }
}

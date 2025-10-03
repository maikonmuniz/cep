package com.test.cep.infra.http;

import com.test.cep.contracts.HttpContract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiAdapter implements HttpContract {

    private final RestTemplate api;

    public ApiAdapter (RestTemplate api) {
        this.api = api;
    }

    @Override
    public String get(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ResponseEntity<String> resp = this.api.getForEntity(url, String.class);
        return resp.getBody();
    }
}

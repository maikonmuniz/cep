package com.test.cep.services;

import com.test.cep.infra.exception.ExceptionAdapter;
import com.test.cep.infra.http.ApiAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CepServicesTest {

    @Mock
    private ApiAdapter apiAdapter;

    @Mock
    private ExceptionAdapter exceptionAdapter;

    @InjectMocks
    private CepServices cepServices;

    @Test
    void testExecute_withNullCep_shouldThrowBadRequestException() {
        String cep = null;
        when(exceptionAdapter.badRequest(anyString())).thenThrow(new RuntimeException("Campo cep está em branco!"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> cepServices.execute(cep));
        assertEquals("Campo cep está em branco!", exception.getMessage());
    }

    @Test
    void testExecute_withEmptyCep_shouldThrowBadRequestException() {
        String cep = "";
        when(exceptionAdapter.badRequest(anyString())).thenThrow(new RuntimeException("Campo cep está em branco!"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> cepServices.execute(cep));
        assertEquals("Campo cep está em branco!", exception.getMessage());
    }

    @Test
    void testExecute_withInvalidCep_shouldThrowBadRequestException() {
        String cep = "1234ABCD";
        when(exceptionAdapter.badRequest(anyString())).thenThrow(new RuntimeException("Campo cep deve conter apenas números!"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> cepServices.execute(cep));
        assertEquals("Campo cep deve conter apenas números!", exception.getMessage());
    }

    @Test
    void testExecute_withCepLengthNotEqualTo8_shouldThrowBadRequestException() {
        String cep = "123456";
        when(exceptionAdapter.badRequest(anyString())).thenThrow(new RuntimeException("Campo cep deve ter 8 dígitos!"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> cepServices.execute(cep));
        assertEquals("Campo cep deve ter 8 dígitos!", exception.getMessage());
    }

    @Test
    void testExecute_withValidCep_shouldCallApiAdapter() {
        String cep = "12345678";
        when(apiAdapter.get(cep)).thenReturn("Resultado da API");
        Object result = cepServices.execute(cep);
        assertNotNull(result);
        assertEquals("Resultado da API", result);
        verify(apiAdapter).get(cep);
    }

    @Test
    void testExecute_withApiAdapterException_shouldThrowInternalServerError() {
        String cep = "12345678";
        when(apiAdapter.get(cep)).thenThrow(new RuntimeException("Erro na API"));
        when(exceptionAdapter.internalServe(anyString())).thenReturn(new RuntimeException("Erro ao processar o CEP: Erro na API"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cepServices.execute(cep));
        assertEquals("Erro ao processar o CEP: Erro na API", exception.getMessage());
    }
}

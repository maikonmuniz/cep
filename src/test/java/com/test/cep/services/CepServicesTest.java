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
    void testExecute_withValidCep_shouldCallApiAdapter() {
        String cep = "12345678";
        when(apiAdapter.Get(cep)).thenReturn("Resultado da API");
        Object result = cepServices.execute(cep);
        assertNotNull(result);
        assertEquals("Resultado da API", result);
        verify(apiAdapter).Get(cep);
    }
}

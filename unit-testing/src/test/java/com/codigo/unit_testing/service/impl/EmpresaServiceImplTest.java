package com.codigo.unit_testing.service.impl;

import com.codigo.unit_testing.aggregates.constants.Constants;
import com.codigo.unit_testing.aggregates.request.EmpresaRequest;
import com.codigo.unit_testing.aggregates.response.BaseResponse;
import com.codigo.unit_testing.dao.EmpresaRepository;
import com.codigo.unit_testing.entity.Empresa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class EmpresaServiceImplTest {
    @Mock
    private EmpresaRepository empresaRepository;
    @InjectMocks
    private EmpresaServiceImpl empresaServiceImpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void  testCrearEmpresaExiste(){
        //ARRANGE
        EmpresaRequest empresaRequest = new EmpresaRequest();
        empresaRequest.setNumeroDocumento("123456789");
            //Configurar el comportamiento del mock
            when(empresaRepository.existsByNumeroDocumento(anyString()))
                    .thenReturn(true);
        //ACT -> EJECUTAR NUESTRO METODO ESPECIFICO
        ResponseEntity<BaseResponse<Empresa>> response = empresaServiceImpl.crear(empresaRequest);

        //ASSERT
        assertEquals(Constants.CODE_EXIST, response.getBody().getCode());
        assertEquals(Constants.MSJ_EXIST, response.getBody().getMessage());
        assertTrue(response.getBody().getObjeto().isEmpty());
    }

    @Test
    void testCrearEmpresaNueva(){
        EmpresaRequest empresaRequest = new EmpresaRequest();
        empresaRequest.setNumeroDocumento("123456789");

        Empresa empresaEsperada = new Empresa();

        //Configurar el comportamiento del mock
        when(empresaRepository.existsByNumeroDocumento(anyString()))
                .thenReturn(false);
        when(empresaRepository.save(any())).thenReturn(empresaEsperada);

        //ACT -> EJECUTAR NUESTRO METODO ESPECIFICO
        ResponseEntity<BaseResponse<Empresa>> response = empresaServiceImpl.crear(empresaRequest);

        //ASSERT
        assertEquals(Constants.CODE_OK, response.getBody().getCode());
        assertEquals(Constants.MSJ_OK, response.getBody().getMessage());
        assertFalse(response.getBody().getObjeto().isEmpty());
        assertSame(empresaEsperada,response.getBody().getObjeto().get());

    }
}
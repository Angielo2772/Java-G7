package com.codigo.Retrofit_Prodriguez.controller;

import com.codigo.Retrofit_Prodriguez.aggregates.response.ResponseSunat;
import com.codigo.Retrofit_Prodriguez.service.InfoSunatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/retrofit/")
@RequiredArgsConstructor
public class InforSunatController {

    private final InfoSunatService infoSunatService;

    @GetMapping("/infoSunat")
    public ResponseEntity<ResponseSunat> getIfnroSunat(
            @RequestParam("ruc") String ruc) throws IOException {
        return new ResponseEntity<>(infoSunatService.getInfoSunat(ruc),
                HttpStatus.OK);

    }
}
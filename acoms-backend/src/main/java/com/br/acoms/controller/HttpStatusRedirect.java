package com.br.acoms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpStatusRedirect {
    @GetMapping("/")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("opa, tou aqui rodando", HttpStatus.OK);
    }
    @GetMapping("/403")
    public ResponseEntity<HttpStatus> forbiden(){
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}

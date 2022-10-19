package com.br.acoms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.Adm;

@RestController
@RequestMapping("/admin")
public class AdmController {
    // @PostMapping("/login")
    // public ResponseEntity<?> loginAdm(@ResponseBody Adm admin){
    //     return new ResponseEntity<String>("entrou", null, HttpStatus.ok);
    // }

    // @PostMapping()
}

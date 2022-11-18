package com.br.acoms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/school")
public class SchoolController {
    @GetMapping("oi")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("opa, tou aq", HttpStatus.OK);
    }
}

package com.br.acoms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.School;
import com.br.acoms.service.SchoolService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;
    
    @PostMapping("")
    public ResponseEntity<String> createSchool(@RequestBody School schoolCreateRequest){
        if(schoolService.readByNameSchool(schoolCreateRequest.getName()) != null){
            return ResponseEntity.badRequest().body("Escola ja existe!");
        }
        schoolService.createSchool(schoolCreateRequest);
        return ResponseEntity.ok("Escola Cadastrada!");
    }

}

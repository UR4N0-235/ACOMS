package com.br.acoms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.payload.request.PersonCreateRequest;
import com.br.acoms.service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    @GetMapping()
    public String helloWorld() {
        return "olaaa mundo";
    }

    @PostMapping()
    public ResponseEntity<String> createPerson(@RequestBody PersonCreateRequest personCreateRequest) {
        if (personService.readByCpfPerson(personCreateRequest.getCpf()) != null
                || personService.readByEmaiPerson(personCreateRequest.getEmail()) != null) {
                    return ResponseEntity.badRequest().body("usuario já existe!");
        }
        personService.createPerson(personCreateRequest);
        return ResponseEntity.ok("Seja bem vindo!");
    }

    
    // @PostMapping("/Salvar")
    // public ResponseEntity<?> savePerson(@RequestBody Optional<Person> pessoa){
    // if(pessoa.isEmpty()){ return ResponseEntity.badRequest().body("dados
    // invalidos!"); }
    // else{
    // pessoa.get().getSenha(encoder.encode());
    // }
    // }

}

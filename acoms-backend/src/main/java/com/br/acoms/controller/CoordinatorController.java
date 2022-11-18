package com.br.acoms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.payload.request.CoordinatorRequest;
import com.br.acoms.service.CoordinatorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coordinator")
public class CoordinatorController {
    private final CoordinatorService coordinatorService;


    @PostMapping("")
    public ResponseEntity<String> createCoordinator(@RequestBody CoordinatorRequest coordinatorCreateRequest) {
        if (coordinatorService.readByCpfPerson(coordinatorCreateRequest.getCpf()) != null
                || coordinatorService.readByEmailPerson(coordinatorCreateRequest.getEmail()) != null) {
            return ResponseEntity.badRequest().body("usuario já existe!");
        }
        coordinatorService.createCoordinator(coordinatorCreateRequest);
        return ResponseEntity.ok("Seja bem vindo!");
    }

    // @GetMapping("")
    // public ResponseEntity<List<Coordinator>> listCoordinators() {
    // }

    // @GetMapping("{id}")
    // public ResponseEntity<Coordinator> loadCoordinator(@PathVariable Long id) {
    // }

    // @PutMapping("{id}")
    // public ResponseEntity<Coordinator> uploadCoordinator(@RequestBody
    // CoordinatorRequest uploadCoordinatorRequest, @PathVariable("id") Long id) {
    // }

    // @DeleteMapping("{id}")
    // public ResponseEntity<HttpStatus> deleteCoordinator(@PathVariable("id") Long
    // id){
    // }
}

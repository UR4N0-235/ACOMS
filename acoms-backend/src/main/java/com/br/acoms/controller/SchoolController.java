package com.br.acoms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.Coordinator;
import com.br.acoms.models.School;
import com.br.acoms.models.payload.request.CoordinatorRequest;
import com.br.acoms.security.jwt.JwtUtils;
import com.br.acoms.service.CoordinatorService;
import com.br.acoms.service.SchoolService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/school")
public class SchoolController {
    private final JwtUtils jwtUtils;
    private final SchoolService schoolService;
    private final CoordinatorService coordinatorService;

    @GetMapping("coordinators")
    public ResponseEntity<List<Coordinator>> listAllCoordinator(HttpServletRequest request){
        if(verifySchool(request) == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        
        School whoami = verifySchool(request);
        // System.out.println("teste 1 " + whoami.getPersons().get(0).getName());        

        List<Coordinator> r = coordinatorService.convertPersonToCoordinator(whoami.getPersons());
        return new ResponseEntity<>(r,HttpStatus.OK);
    }

    @PostMapping("coordinators")
    public ResponseEntity<?> createCoordinator(HttpServletRequest request, @RequestBody CoordinatorRequest coordinatorCreateRequest) {
        // System.out.println("request = " + coordinatorCreateRequest.getName());
        // System.out.println("request = " + coordinatorCreateRequest.getEmail());
        // System.out.println("request = " + coordinatorCreateRequest.getPassword());
        // System.out.println("request = " + coordinatorCreateRequest.getCpf());

        if(verifySchool(request) == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        School whoami = verifySchool(request);
        
        if (coordinatorService.readByCpf(coordinatorCreateRequest.getCpf()).isPresent()
                || coordinatorService.readByEmail(coordinatorCreateRequest.getEmail()).isPresent() ) {
            return ResponseEntity.badRequest().body("usuario já existe!");
        }
        coordinatorCreateRequest.setSchool(whoami);
        coordinatorService.createCoordinator(coordinatorCreateRequest);
        return new ResponseEntity<>("Criado com sucesso !!", HttpStatus.OK);
    }

    private School verifySchool(HttpServletRequest request){
        String jwt = jwtUtils.parseJwt(request);
        Optional<School> isValidSchool = schoolService.readByCnpj(jwtUtils.getUserNameFromJwtToken(jwt));
        
        if(isValidSchool.isEmpty()) return null;
        return isValidSchool.get();
    }
}

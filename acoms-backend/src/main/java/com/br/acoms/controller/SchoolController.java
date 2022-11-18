package com.br.acoms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.Coordinator;
import com.br.acoms.models.School;
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
        String jwt = jwtUtils.parseJwt(request);
        Optional<School> isValidSchool = schoolService.readByCnpj(jwtUtils.getUserNameFromJwtToken(jwt));
        
        if(isValidSchool.isEmpty()) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        
        School whoami = isValidSchool.get();
        System.out.println("teste 1 " + whoami.getPersons().get(0).getName());        

        List<Coordinator> r = coordinatorService.convertPersonToCoordinator(whoami.getPersons());
        return new ResponseEntity<>(r,HttpStatus.OK);
    }

    @GetMapping("/oi")
    public ResponseEntity<String> oi(){
        System.out.println("ta, bateu");
        return new ResponseEntity<>("oi", HttpStatus.OK);
    }

}

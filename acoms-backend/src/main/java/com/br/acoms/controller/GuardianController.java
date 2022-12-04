package com.br.acoms.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.Guardian;
import com.br.acoms.security.jwt.JwtUtils;
import com.br.acoms.service.GuardianService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/guardian")
public class GuardianController {
    private final GuardianService guardianService;
    private final JwtUtils jwtUtils;

    @GetMapping("")
    public ResponseEntity<?> getUserLogged(HttpServletRequest request){
        if(verifyGuardian(request) == null) return new ResponseEntity<>("error no servidor",HttpStatus.INTERNAL_SERVER_ERROR);
        
        return new ResponseEntity<>(verifyGuardian(request), HttpStatus.OK);
    }

    private Guardian verifyGuardian(HttpServletRequest request){
        // System.out.println("verificando coordenador");
        String jwt = jwtUtils.parseJwt(request);
        Optional<Guardian> isValidGuardian = guardianService.readByCpf(jwtUtils.getUserNameFromJwtToken(jwt));
        if(isValidGuardian.isEmpty()) return null;

        return isValidGuardian.get();
    }
}

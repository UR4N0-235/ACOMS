package com.br.acoms.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.Coordinator;
import com.br.acoms.security.jwt.JwtUtils;
import com.br.acoms.service.CoordinatorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coordinator")
public class CoordinatorController {
    private final CoordinatorService coordinatorService;
    private final JwtUtils jwtUtils;

    @GetMapping("")
    public ResponseEntity<?> getUserLogged(HttpServletRequest request){
        // System.out.println("bateu no getMe");
        if(verifyCoordinator(request) == null) return new ResponseEntity<>("error no servidor",HttpStatus.INTERNAL_SERVER_ERROR);
        
        return new ResponseEntity<>(verifyCoordinator(request), HttpStatus.OK);
    }

    // @GetMapping("/chat")
    // public ResponseEntity<?> getChatList(HttpServletRequest request){
    //     String jwt = jwtUtils.parseJwt(request);
    //     Optional<Coordinator> isValidGuardian = coordinatorService.readByCpf(jwtUtils.getUserNameFromJwtToken(jwt));
    
    // }

    private Coordinator verifyCoordinator(HttpServletRequest request){
        // System.out.println("verificando coordenador");
        String jwt = jwtUtils.parseJwt(request);
        Optional<Coordinator> isValidCoordinator = coordinatorService.readByCpf(jwtUtils.getUserNameFromJwtToken(jwt));
        if(isValidCoordinator.isEmpty()) return null;

        return isValidCoordinator.get();
    }
}

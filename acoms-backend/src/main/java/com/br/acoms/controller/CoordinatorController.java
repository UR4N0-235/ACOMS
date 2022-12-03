package com.br.acoms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.Chat;
import com.br.acoms.models.Coordinator;
import com.br.acoms.models.Guardian;
import com.br.acoms.security.jwt.JwtUtils;
import com.br.acoms.service.ChatService;
import com.br.acoms.service.CoordinatorService;
import com.br.acoms.service.GuardianService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coordinator")
public class CoordinatorController {
    private final JwtUtils jwtUtils;
    
    private final CoordinatorService coordinatorService;
    private final GuardianService guardianService;
    private final ChatService chatService;
    

    @GetMapping("")
    public ResponseEntity<?> getUserLogged(HttpServletRequest request){
        // System.out.println("bateu no getMe");
        if(verifyCoordinator(request) == null) return new ResponseEntity<>("error no servidor",HttpStatus.INTERNAL_SERVER_ERROR);
        
        return new ResponseEntity<>(verifyCoordinator(request), HttpStatus.OK);
    }

    @GetMapping("guardians")
    public ResponseEntity<?> getAllGuardiansInMySchool(HttpServletRequest request){
        if(verifyCoordinator(request) == null) return new ResponseEntity<>("Error no servidor!", HttpStatus.INTERNAL_SERVER_ERROR);
        Coordinator loggedCoordinator = verifyCoordinator(request);

        List<Guardian> guardians = guardianService.getAllBySchool(loggedCoordinator.getSchool());
        return new ResponseEntity<>(guardians, HttpStatus.OK);
    }

    @GetMapping("chats")
    public ResponseEntity<?> getAllMyChats(HttpServletRequest request){
        if(verifyCoordinator(request) == null) return new ResponseEntity<>("error servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        
        List<Chat> myChats = chatService.getAllByCoordinator(verifyCoordinator(request));
        if(myChats.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(myChats, HttpStatus.OK);
    }

    private Coordinator verifyCoordinator(HttpServletRequest request){
        // System.out.println("verificando coordenador");
        String jwt = jwtUtils.parseJwt(request);
        Optional<Coordinator> isValidCoordinator = coordinatorService.readByCpf(jwtUtils.getUserNameFromJwtToken(jwt));
        if(isValidCoordinator.isEmpty()) return null;

        return isValidCoordinator.get();
    }
}

package com.br.acoms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.Coordinator;
import com.br.acoms.models.Guardian;
import com.br.acoms.models.Student;
import com.br.acoms.security.jwt.JwtUtils;
import com.br.acoms.service.CoordinatorService;
import com.br.acoms.service.GuardianService;
import com.br.acoms.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/guardian")
public class GuardianController {
    private final StudentService studentService;
    private final GuardianService guardianService;
    private final CoordinatorService coordinatorService;
    private final JwtUtils jwtUtils;

    @GetMapping("")
    public ResponseEntity<?> getUserLogged(HttpServletRequest request){
        if(verifyGuardian(request) == null) return new ResponseEntity<>("error no servidor",HttpStatus.INTERNAL_SERVER_ERROR);
        Guardian loggedGuardian = verifyGuardian(request);
        if(!loggedGuardian.getSchool().getStatusBoolean()) return new ResponseEntity<>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS); 

        return new ResponseEntity<>(loggedGuardian, HttpStatus.OK);
    }

    @GetMapping("students")
    public ResponseEntity<?> getGuradianChildrens(HttpServletRequest request){
        if(verifyGuardian(request) == null) return new ResponseEntity<>("error no servidor",HttpStatus.INTERNAL_SERVER_ERROR);
        Guardian guardianLoggedIs = verifyGuardian(request);
        List<Student> childrens = studentService.getAllByGuardian(guardianLoggedIs);

        // System.out.println(childrens.get(0).getEmail());

        return new ResponseEntity<>(childrens, HttpStatus.OK);
    }

    @GetMapping("coordinators")
    public ResponseEntity<?> listAllCoordinator(HttpServletRequest request){
        if(verifyGuardian(request) == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        List<Coordinator> coordinators = coordinatorService.getAllBySchol(verifyGuardian(request).getSchool());
        for(Coordinator coordinator : coordinators){
            System.out.println("coordinator " + coordinator.getName());
        }
        return new ResponseEntity<>(coordinators,HttpStatus.OK);
    }

    private Guardian verifyGuardian(HttpServletRequest request){
        // System.out.println("verificando coordenador");
        String jwt = jwtUtils.parseJwt(request);
        Optional<Guardian> isValidGuardian = guardianService.readByCpf(jwtUtils.getUserNameFromJwtToken(jwt));
        if(isValidGuardian.isEmpty()) return null;

        return isValidGuardian.get();
    }


}

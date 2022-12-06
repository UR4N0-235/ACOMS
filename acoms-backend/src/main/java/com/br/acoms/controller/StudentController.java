package com.br.acoms.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.Student;
import com.br.acoms.security.jwt.JwtUtils;
import com.br.acoms.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final JwtUtils jwtUtils;

    @GetMapping("")
    public ResponseEntity<?> getUserLogged(HttpServletRequest request){
        if(verifyStudent(request) == null) return new ResponseEntity<>("error no servidor",HttpStatus.INTERNAL_SERVER_ERROR);
        Student loggedGuardian = verifyStudent(request);

        return new ResponseEntity<>(loggedGuardian, HttpStatus.OK);
    }

    private Student verifyStudent(HttpServletRequest request){
        // System.out.println("verificando estudante");
        String jwt = jwtUtils.parseJwt(request);
        Optional<Student> isValidStudent = studentService.readByRm(jwtUtils.getUserNameFromJwtToken(jwt));
        if(isValidStudent.isEmpty()) return null;

        return isValidStudent.get();
    }
}

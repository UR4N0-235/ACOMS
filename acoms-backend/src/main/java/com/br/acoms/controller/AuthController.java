package com.br.acoms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.Roles;
import com.br.acoms.models.payload.Response.JwtResponse;
import com.br.acoms.models.payload.request.LoginRequest;
import com.br.acoms.security.adminSecurity.AdminSecurity;
import com.br.acoms.security.coordinatorSecurity.CoordinatorSecurity;
import com.br.acoms.security.guardianSecurity.GuardianSecurity;
import com.br.acoms.security.jwt.JwtUtils;
import com.br.acoms.security.schoolSecurity.SchoolSecurity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AdminSecurity adminAuthManager;

    @Autowired
    private SchoolSecurity schoolAuthManager;

    @Autowired
    private GuardianSecurity guardianAuthManager;

    @Autowired
    private CoordinatorSecurity coordinatorAuthManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/adminLogin")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest userRequest) {
        try {
            Authentication authentication = adminAuthManager
                    .inMemoryDaoAuthenticationProvider()
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userRequest.getUsername(),
                                    userRequest.getPassword()));

            if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
                    && authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication, userRequest.getUsername(),
                        Roles.ADMIN.toString());

                return new ResponseEntity<>(new JwtResponse(jwt,
                        userRequest.getUsername(), Roles.ADMIN, "admin"), HttpStatus.OK);
            }
            // em caso de badCredentials = nao para o servidor, retorna http status 401
        } catch (BadCredentialsException bad) {
            return new ResponseEntity<>("Failed", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Failed", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/schoolLogin")
    public ResponseEntity<?> authenticateSchool(@RequestBody LoginRequest userRequest) {
        // System.out.println("bateu");
        // System.out.println("username: " + userRequest.getUsername());
        // System.out.println("password: " + userRequest.getPassword());
        
        try {
            Authentication authentication = schoolAuthManager
                    .schoolAuthenticationProvider()
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userRequest.getUsername(),
                                    userRequest.getPassword()));
            if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
                    && authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication, userRequest.getUsername(),
                        Roles.MANAGEMENT.toString());

        //        System.out.println("passou JWTGerado : " + jwt);
        
                return new ResponseEntity<>(new JwtResponse(jwt,
                        userRequest.getUsername(), Roles.MANAGEMENT, "school"), HttpStatus.OK);
            }
            // em caso de badCredentials = nao para o servidor, retorna http status 401
        } catch (BadCredentialsException bad) {
            return new ResponseEntity<>("Failed", HttpStatus.UNAUTHORIZED);
        } catch (HttpMessageNotReadableException notCorrect) {
            return new ResponseEntity<>("Error on getting userInformation", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Failed", HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/guardianLogin")
    public ResponseEntity<?> authenticateGuardian(@RequestBody LoginRequest userRequest) {
        // System.out.println("bateu");
        // System.out.println("username: " + userRequest.getUsername());
        // System.out.println("password: " + userRequest.getPassword());
        
        try {
            Authentication authentication = guardianAuthManager
                    .guardianAuthenticationProvider()
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userRequest.getUsername(),
                                    userRequest.getPassword()));
            if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
                    && authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication, userRequest.getUsername(),
                        Roles.GUARDIAN.toString());

            //    System.out.println("passou JWTGerado : " + jwt);
        
                return new ResponseEntity<>(new JwtResponse(jwt,
                        userRequest.getUsername(), Roles.GUARDIAN, "guardian"), HttpStatus.OK);
            }
            // em caso de badCredentials = nao para o servidor, retorna http status 401
        } catch (BadCredentialsException bad) {
            return new ResponseEntity<>("Failed", HttpStatus.UNAUTHORIZED);
        } catch (HttpMessageNotReadableException notCorrect) {
            return new ResponseEntity<>("Error on getting userInformation", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Failed", HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/coordinatorLogin")
    public ResponseEntity<?> authenticateCoordinator(@RequestBody LoginRequest userRequest) {
        // System.out.println("bateu");
        // System.out.println("username: " + userRequest.getUsername());
        // System.out.println("password: " + userRequest.getPassword());
        
        try {
            Authentication authentication = coordinatorAuthManager
                    .coordinatorAuthenticationProvider()
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userRequest.getUsername(),
                                    userRequest.getPassword()));
            if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
                    && authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication, userRequest.getUsername(),
                        Roles.COORDINATOR.toString());

            //    System.out.println("passou JWTGerado : " + jwt);
        
                return new ResponseEntity<>(new JwtResponse(jwt,
                        userRequest.getUsername(), Roles.COORDINATOR, "coordinator"), HttpStatus.OK);
            }
            // em caso de badCredentials = nao para o servidor, retorna http status 401
        } catch (BadCredentialsException bad) {
            return new ResponseEntity<>("Failed", HttpStatus.UNAUTHORIZED);
        } catch (HttpMessageNotReadableException notCorrect) {
            return new ResponseEntity<>("Error on getting userInformation", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Failed", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ResponseEntity<>("logout", HttpStatus.OK);
    }
}

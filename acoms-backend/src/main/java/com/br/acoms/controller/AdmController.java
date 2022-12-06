package com.br.acoms.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.School;
import com.br.acoms.models.payload.request.SchoolRequest;
import com.br.acoms.service.SchoolService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdmController {
    private final SchoolService schoolService;

    @PostMapping("schools")
    public ResponseEntity<String> createSchool(@RequestBody SchoolRequest schoolCreateRequest) {
        if (schoolService.readByNameSchool(schoolCreateRequest.getName()).isPresent()) return ResponseEntity.badRequest().body("Escola ja existe!");
        if (schoolCreateRequest.getName() == null || schoolCreateRequest.getAddress() == null ||
            schoolCreateRequest.getEmail() == null || schoolCreateRequest.getPassword() == null ||
            schoolCreateRequest.getCnpj() == null || schoolCreateRequest.getTelephoneNumber() == null ||
            schoolCreateRequest.getNameManagement() == null || schoolCreateRequest.getDateOfBirthdayManagement() == null ||
            schoolCreateRequest.getCpfManagement() == null || schoolCreateRequest.getTelephoneNumberManagement() == null
            ) return ResponseEntity.badRequest().body("Atributos Faltando!!!");

        schoolService.createSchool(schoolCreateRequest);
        return new ResponseEntity<>("Escola criada", HttpStatus.CREATED);
    }

    @GetMapping("schools")
    public ResponseEntity<?> listSchools(@RequestParam(required = false) String mudarStatus) {

        if(mudarStatus != null){
            Boolean mudou = schoolService.changeStatusSchool(Long.parseLong(mudarStatus));
            if(mudou) return new ResponseEntity<>(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    
        List<School> schools = schoolService.listAllSchools();

        if(schools.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(schools, HttpStatus.OK);
    
    }

    // @GetMapping("schools/{id}")
    // public ResponseEntity<School> loadSchool(@PathVariable Long id) {
    //     Optional<School> loadedSchool = schoolService.loadSchool(id);

    //     if(!loadedSchool.isPresent()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     return new ResponseEntity<>(loadedSchool.get(), HttpStatus.OK);
    // }

    // @PutMapping("schools/{id}")
    // public ResponseEntity<School> updateSchool(@RequestBody SchoolRequest uploadSchoolRequest, @PathVariable Long id) {
    //         Optional<School> loadedSchool = schoolService.loadSchool(id);

    //         if(!loadedSchool.isPresent() || id == null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    //         return new ResponseEntity<>(schoolService.updateSchool(uploadSchoolRequest, id), HttpStatus.OK);
    // }
    
    // @GetMapping("schools/{id}")
    // public ResponseEntity<HttpStatus> changeStatusSchool(@PathVariable Long id){
    //     System.out.println("teste");
    //     if(!schoolService.changeStatusSchool(id)) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }

    // @DeleteMapping("schools/{id}")
    // public ResponseEntity<HttpStatus> deleteSchool(@PathVariable Long id){
    //     if(!schoolService.deleteSchool(Long.valueOf(id))) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }

    
  
}

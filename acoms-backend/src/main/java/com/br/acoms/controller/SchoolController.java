package com.br.acoms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acoms.models.School;
import com.br.acoms.models.payload.request.SchoolRequest;
import com.br.acoms.service.SchoolService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping("")
    public ResponseEntity<String> createSchool(@RequestBody SchoolRequest schoolCreateRequest) {
        if (schoolService.readByNameSchool(schoolCreateRequest.getName()).isPresent()) {
            return ResponseEntity.badRequest().body("Escola ja existe!");
        }
        schoolService.createSchool(schoolCreateRequest);
        return new ResponseEntity<>("Escola criada", HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<School>> listSchools() {
        List<School> schools = schoolService.listAllSchools();
        
        if(schools.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(schools, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<School> loadSchool(@PathVariable Long id) {
        Optional<School> loadedSchool = schoolService.loadSchool(id);

        if(!loadedSchool.isPresent()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(loadedSchool.get(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<School> uploadSchool(@RequestBody SchoolRequest uploadSchoolRequest, @PathVariable("id") Long id) {
            Optional<School> loadedSchool = schoolService.loadSchool(id);

            if(!loadedSchool.isPresent() || id == null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

            return new ResponseEntity<>(schoolService.updateSchool(uploadSchoolRequest, id), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteSchool(@PathVariable("id") Long id){
        if(!schoolService.deleteSchool(id)) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

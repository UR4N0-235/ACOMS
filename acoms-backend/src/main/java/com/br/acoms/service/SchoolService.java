package com.br.acoms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.acoms.models.School;
import com.br.acoms.models.payload.request.SchoolRequest;
import com.br.acoms.repository.SchoolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public Optional<School> readByNameSchool(String name) {
        return schoolRepository.findByName(name);
    }

    public void createSchool(SchoolRequest schoolCreateRequest) {
        Optional<School> byName = schoolRepository.findByName(schoolCreateRequest.getName());
        if (byName.isPresent()) throw new RuntimeException("School already registred!");
    
        School createdSchool = new School();
        createdSchool.setName(schoolCreateRequest.getName());
        createdSchool.setEndereco(schoolCreateRequest.getEndereco());

        schoolRepository.save(createdSchool);
    }

    public Optional<School> loadSchool(Long id) {
        return schoolRepository.findById(id);
    }

    public List<School> listAllSchools() {
        return schoolRepository.findAll();
    }

    public School updateSchool(SchoolRequest schoolUpdateObject, Long id) {
        Optional<School> loadedSchool = schoolRepository.findById(id);
        if (!loadedSchool.isPresent())
            throw new RuntimeException("School not registred!");

        loadedSchool.get().setName(schoolUpdateObject.getName());
        loadedSchool.get().setEndereco(schoolUpdateObject.getEndereco());

        return schoolRepository.save(loadedSchool.get());
    }

    public boolean deleteSchool(Long id){
        Optional<School> forDelete = schoolRepository.findById(id); 
        
        if(!forDelete.isPresent()) return false;
        schoolRepository.delete(forDelete.get());
        return true; 
    }

}

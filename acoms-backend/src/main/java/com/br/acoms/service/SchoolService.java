package com.br.acoms.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.br.acoms.models.School;
import com.br.acoms.repository.SchoolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public School readByNameSchool(String name){
        return schoolRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    public void createSchool(School schoolCreateRequest){
        Optional<School> byName = schoolRepository.findByName(schoolCreateRequest.getName());
        if(byName.isPresent()){
            throw new RuntimeException("School already registred!");
        }

        schoolRepository.save(schoolCreateRequest);
    }

}

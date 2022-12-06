package com.br.acoms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.acoms.models.School;
import com.br.acoms.models.payload.request.SchoolRequest;
import com.br.acoms.repository.SchoolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Optional<School> readByEmailSchool(String email){
        return schoolRepository.findByEmail(email);
    }

    public Optional<School> readByNameSchool(String name) {
        return schoolRepository.findByName(name);
    }

    public Optional<School> readByCnpj(String cpf){
        return schoolRepository.findByCnpj(cpf);
    }

    public void createSchool(SchoolRequest schoolCreateRequest) {
        Optional<School> byName = schoolRepository.findByName(schoolCreateRequest.getName());
        if (byName.isPresent())
            throw new RuntimeException("School already registred!");

        School createdSchool = new School();
        createdSchool.setName(schoolCreateRequest.getName());
        createdSchool.setAddress(schoolCreateRequest.getAddress());
        createdSchool.setEmail(schoolCreateRequest.getEmail());
        createdSchool.setPassword(encoder.encode(schoolCreateRequest.getPassword()));
        createdSchool.setCnpj(schoolCreateRequest.getCnpj());
        createdSchool.setTelephoneNumber(schoolCreateRequest.getTelephoneNumber());
        createdSchool.setNameManagement(schoolCreateRequest.getNameManagement());
        createdSchool.setDateOfBirthdayManagement(schoolCreateRequest.getDateOfBirthdayManagement());
        createdSchool.setCpfManagement(schoolCreateRequest.getCpfManagement());
        createdSchool.setTelephoneNumberManagement(schoolCreateRequest.getTelephoneNumberManagement());
        createdSchool.setAditionalManagement(schoolCreateRequest.getAditionalManagement());

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
        loadedSchool.get().setAddress(schoolUpdateObject.getAddress());

        return schoolRepository.save(loadedSchool.get());
    }

    public boolean deleteSchool(Long id) {
        Optional<School> forDelete = schoolRepository.findById(id);

        if (!forDelete.isPresent())
            return false;
        schoolRepository.delete(forDelete.get());
        return true;
    }

    public boolean changeStatusSchool(Long id) {
        Optional<School> forChange = schoolRepository.findById(id);

        if (!forChange.isPresent())
            return false;
            
        if(forChange.get().getStatusBoolean()) forChange.get().setStatusBoolean(false);
        else forChange.get().setStatusBoolean(true);

        schoolRepository.save(forChange.get());
        return true;
    }
}

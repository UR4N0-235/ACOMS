package com.br.acoms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.acoms.models.Guardian;
import com.br.acoms.models.Person;
import com.br.acoms.repository.GuardianRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuardianService {
    private final GuardianRepository guardianRepository;

    public Optional<Guardian> readByCpf(String cpf){
       return guardianRepository.findByCpf(cpf);
    }

    public List<Guardian> convertPersonToGuardian(List<Person> persons) {
        System.out.println("guardian service");
        List<Guardian> guardians = new ArrayList<>();
        
        for (Person person : persons) {
            if (guardianRepository.findById(person.getId()).isPresent()) {
                Guardian guardian = guardianRepository.findById(person.getId()).get();
                guardians.add(guardian);
//                System.out.println(guardian.getEmail());
            }

            // nao é um erro - lista todas as pessoas referentes a essa escola, incluindo as que nao sao coordenadores
            // else{
            //     System.out.println("eita error guardianService.convertPersonToGuardian !!!");
            //     System.out.println(person.getEmail());
            // }
        }

        return guardians;
    }
}

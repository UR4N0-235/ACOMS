package com.br.acoms.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.acoms.models.Coordinator;
import com.br.acoms.models.payload.request.CoordinatorRequest;
import com.br.acoms.repository.CoordinatorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoordinatorService {
    private final CoordinatorRepository coordinatorRepository;

    public Optional<Coordinator> readByEmailPerson(String name){
        return coordinatorRepository.findByEmail(name);
    }

    public Optional<Coordinator> readByCpfPerson(String cpf){
        return coordinatorRepository.findByCpf(cpf);
    }

    public Optional<Coordinator> readByRmCoordinator(String rmCoordinator){
        return coordinatorRepository.findByRmCoordinator(rmCoordinator);
    }

    public String generateId(){
        Optional<Coordinator> latest = coordinatorRepository.findTopByOrderByIdDesc();

        return latest.isPresent() ? latest.get().getId() + 1 + "" : "1";
        
    }

    public Coordinator createCoordinator(CoordinatorRequest coordinatorCreateRequest){
        Optional<Coordinator> byEmail = readByEmailPerson(coordinatorCreateRequest.getEmail());
        Optional<Coordinator> byRM = readByRmCoordinator(coordinatorCreateRequest.getRmCoordinator());

        if(byEmail.isPresent() || byRM.isPresent()) throw new RuntimeException("Coordinator already registred!");

        Coordinator create = new Coordinator();
        
        create.setName(coordinatorCreateRequest.getName());
        create.setCpf(coordinatorCreateRequest.getCpf());
        create.setEmail(coordinatorCreateRequest.getEmail());
        create.setPassword(coordinatorCreateRequest.getPassword());
        create.setSchool(coordinatorCreateRequest.getSchool());
        create.setRmCoordinator(generateId());

        return coordinatorRepository.save(create);
    }
}

package com.br.acoms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.acoms.models.Coordinator;
import com.br.acoms.models.Person;
import com.br.acoms.models.Roles;
import com.br.acoms.models.payload.request.CoordinatorRequest;
import com.br.acoms.repository.CoordinatorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoordinatorService {
    // main
    private final CoordinatorRepository coordinatorRepository;

    public Optional<Coordinator> readByEmail(String name) {
        return coordinatorRepository.findByEmail(name);
    }

    public Optional<Coordinator> readByCpf(String cpf) {
        return coordinatorRepository.findByCpf(cpf);
    }

    public Optional<Coordinator> readByRm(String rmCoordinator) {
        return coordinatorRepository.findByRmCoordinator(rmCoordinator);
    }

    public List<Coordinator> convertPersonToCoordinator(List<Person> persons) {
        List<Coordinator> coordinators = new ArrayList<>();
        
        for (Person person : persons) {
            if (coordinatorRepository.findById(person.getId()).isPresent()) {
                Coordinator coordinator = coordinatorRepository.findById(person.getId()).get();
                coordinators.add(coordinator);
//                System.out.println(coordinator.getEmail());
            }

            // nao é um erro - lista todas as pessoas referentes a essa escola, incluindo as que nao sao coordenadores
            // else{
            //     System.out.println("eita error coordinatorService.convertPersonToCoordinator !!!");
            //     System.out.println(person.getEmail());
            // }
        }

        return coordinators;
    }

    public String generateRm() {
        Optional<Coordinator> latest = coordinatorRepository.findTopByOrderByIdDesc();

        return latest.isPresent() ? latest.get().getId() + 1 + "" : "1";

    }

    public Coordinator createCoordinator(CoordinatorRequest coordinatorCreateRequest) {
        Optional<Coordinator> byEmail = readByEmail(coordinatorCreateRequest.getEmail());
        Optional<Coordinator> byRM = readByRm(coordinatorCreateRequest.getRmCoordinator());

        if (byEmail.isPresent() || byRM.isPresent())
            throw new RuntimeException("Coordinator already registred!");

        Coordinator create = new Coordinator();

        create.setName(coordinatorCreateRequest.getName());
        create.setCpf(coordinatorCreateRequest.getCpf());
        create.setEmail(coordinatorCreateRequest.getEmail());
        create.setPassword(coordinatorCreateRequest.getPassword());
        create.setSchool(coordinatorCreateRequest.getSchool());
        create.setRmCoordinator(generateRm());
        create.setRole(Roles.COORDINATOR);

        return coordinatorRepository.save(create);
    }
}

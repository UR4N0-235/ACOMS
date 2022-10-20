package com.br.acoms.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.acoms.models.Person;
import com.br.acoms.models.payload.request.PersonCreateRequest;
import com.br.acoms.repository.PersonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public Person readByCpfPerson(String cpf){
        return personRepository.findByCpf(cpf).orElseThrow(EntityNotFoundException::new);
    }

    public Person readByEmaiPerson(String email){
        return personRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    public void createPerson(PersonCreateRequest personCreateRequest){
        Optional<Person> byCpf = personRepository.findByCpf(personCreateRequest.getCpf());
        Optional<Person> byEmail = personRepository.findByEmail(personCreateRequest.getEmail());

        if(byCpf.isPresent() || byEmail.isPresent()){
            throw new RuntimeException("Person already registred!");
        }

        Person person = new Person();
        person.setName(personCreateRequest.getName());
        person.setCpf(personCreateRequest.getCpf());
        person.setEmail(personCreateRequest.getCpf());
        person.setPassword(bCryptPasswordEncoder.encode(personCreateRequest.getPassword()));

        personRepository.save(person);
    }

}
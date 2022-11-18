package com.br.acoms.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.br.acoms.models.Person;

@Repository
public interface PersonRepository extends PersonBaseRepository{
    Optional<Person> findByName(String name);
    Optional<Person> findByCpf(String cpf);
    Optional<Person> findByEmail(String email);
}

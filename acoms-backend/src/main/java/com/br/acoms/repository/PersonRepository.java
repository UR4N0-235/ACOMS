package com.br.acoms.repository;

import java.util.Optional;

import com.br.acoms.models.Person;

public interface PersonRepository extends PersonBaseRepository{
    Optional<Person> findByName(String name);
    Optional<Person> findByCpf(String cpf);
    Optional<Person> findByEmail(String email);
}

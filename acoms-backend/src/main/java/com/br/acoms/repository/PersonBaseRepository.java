package com.br.acoms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.br.acoms.models.Person;

@NoRepositoryBean
public interface PersonBaseRepository extends JpaRepository<Person, Long>{
    Optional<Person> findByName(String name);
    Optional<Person> findByCpf(String cpf);
    Optional<Person> findByEmail(String email);
}

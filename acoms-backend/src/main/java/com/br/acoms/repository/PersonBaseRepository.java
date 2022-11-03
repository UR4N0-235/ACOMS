package com.br.acoms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.br.acoms.models.Person;

@NoRepositoryBean
public interface PersonBaseRepository extends JpaRepository<Person, Long>{
 
}

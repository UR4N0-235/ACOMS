package com.br.acoms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.acoms.models.School;

public interface SchoolRepository extends JpaRepository<School, Long>{
    Optional<School> findByName(String name);
}

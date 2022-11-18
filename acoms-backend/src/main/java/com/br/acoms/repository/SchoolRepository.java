package com.br.acoms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.acoms.models.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long>{
    Optional<School> findByName(String name);
    Optional<School> findByEmail(String email);
    Optional<School> findByCnpj(String cnpj);
}

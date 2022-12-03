package com.br.acoms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.acoms.models.Guardian;

@Repository
public interface GuardianRepository extends JpaRepository<Guardian, Long>{
    Optional<Guardian> findByEmail(String email);
    Optional<Guardian> findByCpf(String cpf);
    Optional<Guardian> findById(Long id);
}

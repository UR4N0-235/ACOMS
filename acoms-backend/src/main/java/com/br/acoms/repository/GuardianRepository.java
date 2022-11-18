package com.br.acoms.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.br.acoms.models.Guardian;

@Repository
public interface GuardianRepository extends PersonBaseRepository {
    Optional<Guardian> findByEmail(String email);
}

package com.br.acoms.repository;

import java.util.Optional;

import com.br.acoms.models.Guardian;

public interface GuardianRepository extends PersonBaseRepository {
    Optional<Guardian> findByEmail(String email);
}

package com.br.acoms.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.br.acoms.models.Coordinator;

@Repository
public interface CoordinatorRepository extends PersonBaseRepository{
    Optional<Coordinator> findByRmCoordinator(String rmCoordinator);
    Optional<Coordinator> findByEmail(String name);
    Optional<Coordinator> findByCpf(String cpf);
    Optional<Coordinator> findTopByOrderByIdDesc();
    //Optional<Coordinator> findById(Long id);
}

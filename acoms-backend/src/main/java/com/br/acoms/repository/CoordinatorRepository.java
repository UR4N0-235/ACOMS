package com.br.acoms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.acoms.models.Coordinator;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long>{
    Optional<Coordinator> findByRmCoordinator(String rmCoordinator);
    Optional<Coordinator> findByEmail(String name);
    Optional<Coordinator> findByCpf(String cpf);
    Optional<Coordinator> findTopByOrderByIdDesc();
    Optional<Coordinator> findById(Long id);
}

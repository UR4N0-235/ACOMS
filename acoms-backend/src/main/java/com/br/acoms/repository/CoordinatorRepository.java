package com.br.acoms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.acoms.models.Coordinator;
import com.br.acoms.models.School;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long>{
    Optional<Coordinator> findByRmCoordinator(String rmCoordinator);
    Optional<Coordinator> findByEmail(String name);
    Optional<Coordinator> findByCpf(String cpf);
    Optional<Coordinator> findTopByOrderByIdDesc();
    Optional<Coordinator> findById(Long id);
    Optional<List<Coordinator>> findBySchool(School school);
}

package com.br.acoms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.acoms.models.Chat;
import com.br.acoms.models.Coordinator;
import com.br.acoms.models.Guardian;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{
    Optional<List<Chat>> findByCoordinator(Coordinator coordinator);
    Optional<Chat> findByGuardian(Guardian guardian);
}

package com.br.acoms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.acoms.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
}

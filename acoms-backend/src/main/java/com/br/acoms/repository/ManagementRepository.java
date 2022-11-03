package com.br.acoms.repository;

import java.util.Optional;

import com.br.acoms.models.Management;

public interface ManagementRepository extends PersonBaseRepository{
    Optional<Management> findByRmManagement(String rmManagement);
}

package com.br.acoms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.acoms.models.Guardian;
import com.br.acoms.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<List<Student>> findByParent(Guardian guardian);
}

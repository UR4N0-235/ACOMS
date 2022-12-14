package com.br.acoms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.acoms.models.Guardian;
import com.br.acoms.models.Student;
import com.br.acoms.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService{
    private final StudentRepository studentRepository;

    public List<Student> getAllByGuardian(Guardian guardian){
        Optional<List<Student>> gettedThisStudents = 
        studentRepository.findByParent(guardian);
        if(gettedThisStudents.isPresent()){
            return gettedThisStudents.get();
        }else{
            return null;
        }
    }

    public Optional<Student> readByRm(Long rmStudent){
        Optional<Student> student = studentRepository.findByRmStudent(rmStudent);
        if(student.isEmpty()) return null;
        return student;
    }

    public Optional<Student> readByRm(String rmStudent){
        Optional<Student> student = studentRepository.findByRmStudent(Long.parseLong(rmStudent));
        if(student.isEmpty()) return null;
        return student;
    }
}
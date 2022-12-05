package com.br.acoms.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter @Setter @RequiredArgsConstructor
public class Student extends Person{
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id", updatable = false)
    // private Long idStudent;
    
    // usando o rm aluno como um id proprio do aluno
    @Column(name = "rmStudent",nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rmStudent;

    @ManyToMany(mappedBy = "childrens")
    private List<Guardian> parents;

    public Student(String name, String cpf, String email, String password, Date dateOfBirthday, String telephoneNumber,
            String profilePhoto, School school, Roles role, String address, List<Guardian> parents, Long rmStudent) {
        super(name, cpf, email, password, dateOfBirthday, telephoneNumber, profilePhoto, school, role, address);
        this.parents = parents;
        this.rmStudent = rmStudent;
    }

    
}
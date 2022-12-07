package com.br.acoms.models;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @JsonIgnoreProperties("hibernateLazyInitializer")
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.REMOVE, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_guardian")
    private Guardian parent;

    public Student(String name, String cpf, String email, String password, Date dateOfBirthday, String telephoneNumber,
            String profilePhoto, School school, Roles role, String address, Guardian parent, Long rmStudent) {
        super(name, cpf, email, password, dateOfBirthday, telephoneNumber, profilePhoto, school, role, address);
        this.parent = parent;
        this.rmStudent = rmStudent;
    }

    
}
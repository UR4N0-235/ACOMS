package com.br.acoms.models;

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
@Table
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
}
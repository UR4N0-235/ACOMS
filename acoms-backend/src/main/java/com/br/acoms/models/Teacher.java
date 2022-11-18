package com.br.acoms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter @Setter @RequiredArgsConstructor
public class Teacher extends Person {
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id", updatable = false)
    // private Long idTeacher;

    // usando rm teacher como um id proprio da classe
    @Column(name = "rmTeacher",nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rmTeacher;
}

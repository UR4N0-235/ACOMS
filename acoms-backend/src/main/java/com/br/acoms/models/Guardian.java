package com.br.acoms.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter @Setter @RequiredArgsConstructor
public class Guardian extends Person{
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id", updatable = false)
    // private Long idGuardian;

    @Column(name = "rmGuardian", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rm_guardian_seq")
    private Long rmGuardian;

    @ManyToMany
    @JoinTable(
        name = "responsability",
        joinColumns = @JoinColumn(name = "id_guardian"),
        inverseJoinColumns = @JoinColumn(name = "id_student")
    )
    private List<Student> childrens;

}
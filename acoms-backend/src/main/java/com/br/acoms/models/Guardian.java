package com.br.acoms.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "guardian")
@Getter @Setter @RequiredArgsConstructor
public class Guardian extends Person{
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id", updatable = false)
    // private Long idGuardian;

    @Column(name = "rmGuardian", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rm_guardian_seq")
    private Long rmGuardian;

    @JsonBackReference
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> childrens;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "guardian")
    private Chat chat;

    public Guardian(String name, String cpf, String email, String password, Date dateOfBirthday, String telephoneNumber,
            String profilePhoto, School school, Roles role, String address, Long rmGuardian) {
        super(name, cpf, email, password, dateOfBirthday, telephoneNumber, profilePhoto, school, role, address);
        this.rmGuardian = rmGuardian;
    }
}
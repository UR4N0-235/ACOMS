package com.br.acoms.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coordinator")
@Getter @Setter @RequiredArgsConstructor
public class Coordinator extends Person {
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id", updatable = false)
    // private Long idCoordinator;

    //usando o rm como se fosse um id proprio do coordenador
    @Column(name = "rmCoordinator", unique = true, updatable = false)
    private String rmCoordinator;

    @JsonBackReference
    @OneToMany(mappedBy = "coordinator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chat> chatList;


    public Coordinator(String name, String cpf, String email, String password, Date dateOfBirthday,
            String telephoneNumber, String profilePhoto, String address, School school, Roles role, String rm) {
        super(name, cpf, email, password, dateOfBirthday, telephoneNumber, profilePhoto, school, role, address);
        this.rmCoordinator = rm;
    }
}
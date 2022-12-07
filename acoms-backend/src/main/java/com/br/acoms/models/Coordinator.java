package com.br.acoms.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coordinator")
@Getter @Setter @RequiredArgsConstructor
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Coordinator extends Person {
    @Column(name = "rmCoordinator", unique = true, updatable = false)
    private String rmCoordinator;

    @JsonBackReference
    @OneToMany(mappedBy = "coordinator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chat> chatList;

    @Column(name = "cargo")
    private String cargo;

    public Coordinator(String name, String cargo, String cpf, String email, String password, Date dateOfBirthday,
            String telephoneNumber, String profilePhoto, String address, School school, Roles role, String rm) {
        super(name, cpf, email, password, dateOfBirthday, telephoneNumber, profilePhoto, school, role, address);
        this.rmCoordinator = rm;
        this.cargo = cargo;
    }
}
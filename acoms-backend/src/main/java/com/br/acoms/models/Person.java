package com.br.acoms.models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* this class subtistute 'ApiUser' in some others projects models */

@Entity
@Table(name = "person", uniqueConstraints = {
    @UniqueConstraint(name = "person_cpf_unique", columnNames = "cpf"),
    @UniqueConstraint(name = "person_email_unique", columnNames = "email")
})
@Inheritance(strategy = InheritanceType.JOINED)
@Data @AllArgsConstructor @NoArgsConstructor
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_person_seq")
    @SequenceGenerator(
        name = "id_person_seq",
        sequenceName = "id_person_seq",
        allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    
    @Column(name = "cpf", unique = true, nullable = false, columnDefinition = "TEXT")
    private String cpf;
    
    @Column(name = "email", unique = true, nullable = false, columnDefinition = "TEXT")
    private String email;
    
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;
    
    @Column(name = "dateOfBirthday", columnDefinition = "TEXT")
    private Date dateOfBirthday;
    
    @Column(name = "telephoneNumber", columnDefinition = "TEXT")
    private String telephoneNumber;
    
    @Column(name = "profilePhoto", columnDefinition = "TEXT")
    private String profilePhoto;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.REMOVE, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_school")
    private School school; 

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    public Person(String name, String cpf, String email, String password, Date dateOfBirthday, String telephoneNumber,
            String profilePhoto, School school, Roles role) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.dateOfBirthday = dateOfBirthday;
        this.telephoneNumber = telephoneNumber;
        this.profilePhoto = profilePhoto;
        this.school = school;
        this.role = role;
    }
}

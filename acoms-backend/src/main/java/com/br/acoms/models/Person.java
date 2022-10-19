package com.br.acoms.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* this class subtistute 'ApiUser' in some others projects models */

@Entity(name = "person")
@Table(name = "Person", uniqueConstraints = {
    @UniqueConstraint(name = "person_cpf_unique", columnNames = "cpf"),
    @UniqueConstraint(name = "person_email_unique", columnNames = "email")
})
@Data @AllArgsConstructor @NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
        name = "person_sequence",
        sequenceName = "person_sequence",
        allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private long id;

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
}

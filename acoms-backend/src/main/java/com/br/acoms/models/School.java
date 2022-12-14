package com.br.acoms.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "school", uniqueConstraints = {
        @UniqueConstraint(name = "school_name_unique", columnNames = "name"),
        @UniqueConstraint(name = "school_email_unique", columnNames = "email")
})
// @Setter @Getter @AllArgsConstructor @NoArgsConstructor
@Data @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long idSchool;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "status")
    private Boolean statusBoolean;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "cnpj", unique = true, updatable = false, nullable = false)
    private String cnpj;

    @Column(name = "telephoneNumber", unique = true, nullable = false)
    private String telephoneNumber;

    // start management
    @Column(name = "nameManagement")
    private String nameManagement;

    @Temporal(TemporalType.DATE)
    @Column(name = "dateOfBirthdayManagement")
    private Date dateOfBirthdayManagement;

    @Column(name = "cpfManagement", unique = true, nullable = false)
    private String cpfManagement;

    @Column(name = "telephoneNumberManagement")
    private String telephoneNumberManagement;
    
    @Column(name ="aditionalManagement")
    private String aditionalManagement;
    // end management
    
    @JsonBackReference
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> persons;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Planos contrato;

    public School(String name, String address, String email, String password, String cnpj, String telephoneNumber,
            String nameManagement, Date dateOfBirthdayManagement, String cpfManagement,
            String telephoneNumberManagement, String aditionalManagement, Planos contrato, Boolean status) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.cnpj = cnpj;
        this.telephoneNumber = telephoneNumber;
        this.nameManagement = nameManagement;
        this.dateOfBirthdayManagement = dateOfBirthdayManagement;
        this.cpfManagement = cpfManagement;
        this.telephoneNumberManagement = telephoneNumberManagement;
        this.aditionalManagement = aditionalManagement;
        this.statusBoolean = status;
        this.contrato = contrato;
    }

    public enum Planos{
        BASICO, PROFISSIONAL, VIP
    }
}

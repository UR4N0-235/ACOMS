package com.br.acoms.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;

@Entity(name = "Guardian")
@Table(name = "Guardian")
public class Guardian extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guardian_id", updatable = false)
    private Long id;
    
    @ManyToMany
    @JoinTable(
        name = "responsability",
        joinColumns = @JoinColumn(name = "guardian_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> childrens;

}
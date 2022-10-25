package com.br.acoms.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Student")
@Table(name = "Student")

public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", updatable = false)
    private Long id;
    
    @ManyToMany(mappedBy = "childrens")
    private List<Guardian> parents;
}
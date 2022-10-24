package com.br.acoms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Adm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
        name = "adm_sequence",
        sequenceName = "adm_sequence",
        allocationSize = 1
    )
    @Column(name = "id", updatable = false)
    private long id;

    @Column()
    private String login;
    
    @Column()
    private String password;
}

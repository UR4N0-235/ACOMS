package com.br.acoms.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chat")
@Getter @Setter @RequiredArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties("hibernateLazyInitializer")
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.REMOVE, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coordinator")
    private Coordinator coordinator;
    
    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_guardian", nullable = false, unique = true)
    private Guardian guardian;
    
    @JsonBackReference
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Message> messages;

    public Chat(Coordinator coordinator, Guardian guardian) {
        this.coordinator = coordinator;
        this.guardian = guardian;
    }      
    
    
}

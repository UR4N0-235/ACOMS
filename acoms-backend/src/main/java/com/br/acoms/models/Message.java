package com.br.acoms.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "message")
@Getter @Setter @RequiredArgsConstructor
public class Message{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "enviadoPor")
    private EnviadoPor enviado;

    @JsonIgnoreProperties("hibernateLazyInitializer")
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.REMOVE, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chat")
    private Chat chat;

    public Message(String message, Date date, Chat chat, EnviadoPor enviado) {
        this.message = message;
        this.date = date;
        this.chat = chat;
        this.enviado = enviado;
    }

    public enum EnviadoPor{
        RESPONSAVEL, COORDENADOR
    }
}
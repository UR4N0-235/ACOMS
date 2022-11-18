package com.br.acoms.models.payload.request;

import com.br.acoms.models.School;

import lombok.Data;

@Data
public abstract class PersonRequest {
    private String name;
    private String cpf;
    private String email;
    private String password;
    private School school;
}

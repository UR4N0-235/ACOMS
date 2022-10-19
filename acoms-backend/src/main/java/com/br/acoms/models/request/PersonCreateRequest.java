package com.br.acoms.models.request;

import lombok.Data;

@Data
public class PersonCreateRequest {
    private String name;
    private String email;
    private String cpf;
    private String password;
}

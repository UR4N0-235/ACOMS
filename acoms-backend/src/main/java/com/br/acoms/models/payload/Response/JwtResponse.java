package com.br.acoms.models.payload.Response;

import com.br.acoms.models.Roles;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private Roles role;

    public JwtResponse(String token, Long id, String username, Roles role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.role = role;
    }

    
}

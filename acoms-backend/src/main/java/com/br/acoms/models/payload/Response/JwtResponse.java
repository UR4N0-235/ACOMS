package com.br.acoms.models.payload.Response;

import com.br.acoms.models.Roles;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private Roles role;
    private int defaultDaysToExpire = 1;
    private String path;

    public JwtResponse(String token, String username, Roles role, String path) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.path = path;
    }   
}

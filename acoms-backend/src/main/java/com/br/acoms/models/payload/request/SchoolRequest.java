package com.br.acoms.models.payload.request;

import lombok.Data;

/* request to create and update methods */

@Data
public class SchoolRequest {
    private String name;
    private String address;
}

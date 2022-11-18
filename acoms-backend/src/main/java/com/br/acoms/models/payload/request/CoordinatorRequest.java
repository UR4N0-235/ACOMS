package com.br.acoms.models.payload.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class CoordinatorRequest extends PersonRequest{
    private String rmCoordinator;
}

package com.br.acoms.security.coordinatorSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CoordinatorSecurity {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public CoordinatorUserDetailsService coordinatorUserDetailsService;

    @Bean
    public DaoAuthenticationProvider coordinatorAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(coordinatorUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(encoder);
        return daoAuthenticationProvider;
    }        
}

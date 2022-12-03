package com.br.acoms.security.guardianSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class GuardianSecurity {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public GuardianUserDetailsService guardianUserDetailsService;

    @Bean
    public DaoAuthenticationProvider guardianAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(guardianUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(encoder);
        return daoAuthenticationProvider;
    }    
}

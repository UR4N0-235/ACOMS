package com.br.acoms.security.schoolSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SchoolSecurity {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public SchoolUserDetailsService schoolUserDetailsService;

    @Bean
    public DaoAuthenticationProvider schoolAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(schoolUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(encoder);
        return daoAuthenticationProvider;
    }
}

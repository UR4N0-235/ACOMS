package com.br.acoms.security.adminSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import com.br.acoms.models.Roles;

@Component
public class AdminSecurity {
    @Autowired
    private PasswordEncoder encoder;

    @Bean
    public InMemoryUserDetailsManager adminDetailsService() {
        UserDetails user = User
                .withUsername("admin")
                .password(encoder.encode("admin"))
                .roles(Roles.ADMIN.toString())
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public DaoAuthenticationProvider inMemoryDaoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(adminDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(encoder);

        return daoAuthenticationProvider;
    }    
}

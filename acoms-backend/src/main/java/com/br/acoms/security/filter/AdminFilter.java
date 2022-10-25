package com.br.acoms.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.br.acoms.security.CustomAuthenticationProvider;
import com.br.acoms.security.WebSecurityConfig;

@Configuration
public class AdminFilter {
@Autowired
WebSecurityConfig authManager;

    @Bean
    public SecurityFilterChain filterChainApp1(HttpSecurity http) throws Exception {
        http.antMatcher("/admin*")
          .authorizeRequests()
          .anyRequest()
          .hasRole("ADMIN")

          .and()
          .formLogin()
          .loginPage("/loginAdmin")
          .loginProcessingUrl("/admin_login")
          .failureUrl("/loginAdmin?error=loginError")
          .defaultSuccessUrl("/adminPage")
          
          .and()
          .logout()
          .logoutUrl("/admin_logout")
          .logoutSuccessUrl("/protectedLinks")
          .deleteCookies("JSESSIONID")
          
          .and()
          .exceptionHandling()
          .accessDeniedPage("/403")
          
          .and()
          .authenticationManager(authManager.authManager(http))

          .csrf().disable();
       return http.build();
    }    
}

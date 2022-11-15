package com.br.acoms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.acoms.security.adminSecurity.AdminSecurity;
import com.br.acoms.security.schoolSecurity.SchoolSecurity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MultipleSecurityController {
    @Autowired
    private final AdminSecurity adminSecurity;

    @Autowired
    private final SchoolSecurity schoolSecurity;

    public DaoAuthenticationProvider adminProvider() {
        return adminSecurity.inMemoryDaoAuthenticationProvider();
    }

    public DaoAuthenticationProvider schoolProvider() {
        return schoolSecurity.schoolAuthenticationProvider();
    }

    public UserDetails defineUserDetails(String username) {
        try {
            if (adminSecurity.adminDetailsService().loadUserByUsername(username) != null)
                return adminSecurity.adminDetailsService().loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
        }
        if (schoolSecurity.schoolUserDetailsService.loadUserByUsername(username) != null)
            return schoolSecurity.schoolUserDetailsService.loadUserByUsername(username);

        return null;
    }

}

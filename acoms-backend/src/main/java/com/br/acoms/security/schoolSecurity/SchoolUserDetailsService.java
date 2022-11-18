package com.br.acoms.security.schoolSecurity;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.acoms.models.School;
import com.br.acoms.service.SchoolService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolUserDetailsService implements UserDetailsService {
    private final SchoolService schoolService;

    @Override
    public UserDetails loadUserByUsername(String cnpj) throws UsernameNotFoundException {

        Optional<School> school = schoolService.readByCnpj(cnpj);

        if (school.isEmpty())
            throw new UsernameNotFoundException("No user found with the given email");
        return SchoolUserDetails.build(school.get());
    }
}

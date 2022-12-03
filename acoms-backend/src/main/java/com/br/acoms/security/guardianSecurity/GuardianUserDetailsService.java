package com.br.acoms.security.guardianSecurity;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.acoms.models.Guardian;
import com.br.acoms.service.GuardianService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuardianUserDetailsService implements UserDetailsService{
    private final GuardianService guardianService;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException  {

        Optional<Guardian> guardian = guardianService.readByCpf(cpf);

        if (guardian.isEmpty())
            throw new UsernameNotFoundException("No user found with the given email");        ;
        return GuardianUserDetails.build(guardian.get());
    }
}

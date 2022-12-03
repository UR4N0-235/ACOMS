package com.br.acoms.security.coordinatorSecurity;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.acoms.models.Coordinator;
import com.br.acoms.service.CoordinatorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoordinatorUserDetailsService implements UserDetailsService{
    private final CoordinatorService coordinatorService;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException{

        Optional<Coordinator> coordinator = coordinatorService.readByCpf(cpf);

        if (coordinator.isEmpty())
            throw new UsernameNotFoundException("No user found with the given email");        ;
        return CoordinatorUserDetails.build(coordinator.get());
    }

}

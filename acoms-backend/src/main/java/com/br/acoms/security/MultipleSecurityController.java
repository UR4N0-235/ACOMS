package com.br.acoms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.acoms.security.adminSecurity.AdminSecurity;
import com.br.acoms.security.coordinatorSecurity.CoordinatorSecurity;
import com.br.acoms.security.guardianSecurity.GuardianSecurity;
import com.br.acoms.security.schoolSecurity.SchoolSecurity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MultipleSecurityController {
    @Autowired
    private final AdminSecurity adminSecurity;

    @Autowired
    private final SchoolSecurity schoolSecurity;

    @Autowired
    private final GuardianSecurity guardianSecurity;

    @Autowired
    private final CoordinatorSecurity coordinatorSecurity;


    public DaoAuthenticationProvider adminProvider() {
        return adminSecurity.inMemoryDaoAuthenticationProvider();
    }

    public DaoAuthenticationProvider schoolProvider() {
        return schoolSecurity.schoolAuthenticationProvider();
    }

    public DaoAuthenticationProvider coordinatorProvider(){
        return coordinatorSecurity.coordinatorAuthenticationProvider();
    }

    public DaoAuthenticationProvider guardianProvider(){
        return guardianSecurity.guardianAuthenticationProvider();
    }

    public UserDetails defineUserDetails(String username) {
        if(isAdmin(username) != null) return isAdmin(username);
        if(isSchool(username) != null) return isSchool(username);
        if(isGuardian(username) != null) return isGuardian(username);
        if(isCoordinator(username) != null) return isCoordinator(username);

        // se nao encontrar nenhum userDetail
        System.out.println("opa, nao encontrou nenhum UserDetail ! ! ! eita eita ein, arruma isso ai");
        return null;
    }

    private UserDetails isAdmin(String username){
        try {
            if (adminSecurity.adminDetailsService().loadUserByUsername(username) != null)
                return adminSecurity.adminDetailsService().loadUserByUsername(username);
        } catch (UsernameNotFoundException e){
            // tratamento de erro?
            return null;
        }
        return null;
    }

    private UserDetails isSchool(String username){
        try{
            if (schoolSecurity.schoolUserDetailsService.loadUserByUsername(username) != null)
                return schoolSecurity.schoolUserDetailsService.loadUserByUsername(username);
        }catch(UsernameNotFoundException e){
            // tratamento de erro?
            return null;
        }
        return null;
    }
    
    private UserDetails isCoordinator(String username){
        // System.out.println("username isCoordinator: "+username);
        try {
            if (coordinatorSecurity.coordinatorUserDetailsService.loadUserByUsername(username) != null)
                return coordinatorSecurity.coordinatorUserDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e){
            // tratamento de erro?
            return null;
        }
        return null;
    }

    private UserDetails isGuardian(String username){
        try{
            if (guardianSecurity.guardianUserDetailsService.loadUserByUsername(username) != null)
            return guardianSecurity.guardianUserDetailsService.loadUserByUsername(username);
        }catch(UsernameNotFoundException e){
            // tratamento de erro?
            return null;
        }
        return null;
    }

}

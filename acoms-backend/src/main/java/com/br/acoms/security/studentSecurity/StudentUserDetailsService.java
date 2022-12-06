package com.br.acoms.security.studentSecurity;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.acoms.models.Student;
import com.br.acoms.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentUserDetailsService implements UserDetailsService{
    private final StudentService studentService;

    @Override
    public UserDetails loadUserByUsername(String rm) throws UsernameNotFoundException  {

        Optional<Student> student = studentService.readByRm(rm);

        if (student.isEmpty())
            throw new UsernameNotFoundException("No user found with the given email");        ;
        return StudentUserDetails.build(student.get());
    }    
}

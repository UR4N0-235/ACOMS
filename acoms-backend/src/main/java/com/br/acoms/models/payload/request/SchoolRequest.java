package com.br.acoms.models.payload.request;

import java.sql.Date;
import java.util.List;

import com.br.acoms.models.Person;

import lombok.Data;

/* request to create and update methods */

@Data
public class SchoolRequest {
    private Long idSchool;
    private String name;
    private String address;
    private String email;
    private String password;
    private String cnpj;
    private String telephoneNumber;
    // start management
    private String nameManagement;
    private Date dateOfBirthdayManagement;
    private String cpfManagement;
    private String telephoneNumberManagement;
    private String aditionalManagement;
    // end management
    private List<Person> persons;
}

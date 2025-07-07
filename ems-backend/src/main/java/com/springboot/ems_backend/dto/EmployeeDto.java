package com.springboot.ems_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeDto {

    // We use EmployeeDto class to transfer the data b/w client and server
    // When we build restful services, we'll return dto objects
    // We need a mapper to map dto to entity and then vice-versa
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}

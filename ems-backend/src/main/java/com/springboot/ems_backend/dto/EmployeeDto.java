package com.springboot.ems_backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

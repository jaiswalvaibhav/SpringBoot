package com.springboot.ems_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity //Tells JPA to map this class to a db table
@Table(name = "employees") //to specify the table details; If not present, hibernate uses the class name
public class Employee {//POJO

    /*
        Hibernate generates SQL DDL (Data Definition Language) statements from your entities.
        For example:
        create table employees (
           id serial primary key,
           name varchar(255),
           department varchar(255)
        );
    */

    @Id // to make id as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key generation strategy; IDENTITY uses database auto increment feature to increase the primary key automatically
    private Long id;

    @Column(name = "first_name") //if we don't give column here, JPA will give column name as field name i.e. firstName
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id", nullable = false, unique = true) //email is mandatory and should be unique for all employees
    private String email;
}

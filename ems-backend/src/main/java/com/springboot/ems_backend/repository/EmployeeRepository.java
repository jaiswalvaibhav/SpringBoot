package com.springboot.ems_backend.repository;

import com.springboot.ems_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {// JpaRepository<entity, type of primary key>

    // Now this EmployeeRepository will have CRUD db operations on Employee JPA Entity
}

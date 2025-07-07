package com.springboot.ems_backend.service;

import com.springboot.ems_backend.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);
}

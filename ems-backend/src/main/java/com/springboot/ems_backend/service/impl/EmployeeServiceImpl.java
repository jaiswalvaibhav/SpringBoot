package com.springboot.ems_backend.service.impl;

import com.springboot.ems_backend.dto.EmployeeDto;
import com.springboot.ems_backend.entity.Employee;
import com.springboot.ems_backend.mapper.EmployeeMapper;
import com.springboot.ems_backend.repository.EmployeeRepository;
import com.springboot.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        //Firstly convert employee dto to JPA entity

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
}

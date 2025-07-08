package com.springboot.ems_backend.controller;

import com.springboot.ems_backend.dto.EmployeeDto;
import com.springboot.ems_backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    // Build add employee rest api
    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build get employee by id rest api
    @GetMapping("/get")
    public ResponseEntity<EmployeeDto> getEmployeeById(@RequestParam("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        /*
        Q- Why is no-args constructor needed in the entity and dto?

        Ans:
        i) When Hibernate loads an Employee from the database, it does not call your own constructor with arguments.
        It uses reflection and expects:
           1) a public or protected no-args constructor (default constructor).
           2) then it sets the fields one by one.
        Thus, we add @NoArgsConstructor on Employee

        ii) When you return the EmployeeDto in your ResponseEntity, Spring (with Jackson) serializes it into JSON.
        Similarly, if at any point Spring or Jackson deserializes JSON into an EmployeeDto, it also needs a default constructor.
        Jackson:
           1) creates an empty object using the default constructor
           2) then sets properties via setters (or reflection).

        Summary:
        | Class                 | Why Default Constructor Needed?                                                   |
        | --------------------- | --------------------------------------------------------------------------------- |
        | **Employee (Entity)** | JPA / Hibernate needs it to instantiate via reflection                            |
        | **EmployeeDto (DTO)** | Jackson (or any deserialization framework) needs it to instantiate and set fields |

        */
        return ResponseEntity.ok(employeeDto);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDtoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok(updatedEmployeeDto);
    }

    // Build delete api rest method
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}

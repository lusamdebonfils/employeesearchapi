package com.perficeint.employeesearchapi.service;


import com.perficeint.employeesearchapi.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IemployeeService {
    List<Employee> getAllEmployees();
    Employee createEmployee(Employee employee);
    Employee findEmployeeById(String id);
    Employee updateEmployeeById(String id);
    Employee deleteEmployeeById(String id);

}

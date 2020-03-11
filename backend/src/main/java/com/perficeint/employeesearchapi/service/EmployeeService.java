package com.perficeint.employeesearchapi.service;

import com.perficeint.employeesearchapi.model.Employee;
import com.perficeint.employeesearchapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IemployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findEmployeeById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployeeById(String id) {
        Employee updatedEmployee = employeeRepository.findById(id).orElse(null);
        if(updatedEmployee == null) return null;
        employeeRepository.save(updatedEmployee);
        return updatedEmployee;
    }

    @Override
    public Employee deleteEmployeeById(String id) {
        Employee deletedEmployee = employeeRepository.findById(id).orElse(null);
        if(deletedEmployee == null) return null;
        employeeRepository.delete(deletedEmployee);
        return deletedEmployee;
    }
}

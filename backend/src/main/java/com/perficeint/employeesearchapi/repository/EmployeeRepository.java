package com.perficeint.employeesearchapi.repository;

import com.perficeint.employeesearchapi.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EmployeeRepository extends MongoRepository<Employee,String> {
}

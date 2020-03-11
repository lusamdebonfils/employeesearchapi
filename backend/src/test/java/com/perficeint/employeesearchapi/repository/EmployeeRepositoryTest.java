package com.perficeint.employeesearchapi.repository;

import com.perficeint.employeesearchapi.model.Address;
import com.perficeint.employeesearchapi.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataMongoTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSave(){
        Address address = Address.builder()
                ._id(UUID.randomUUID())
                .city("Fairfield")
                .country("USA")
                .suite("405")
                .street("1000 N St")
                .region("Central America")
                .postal("60025")
                .build();
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .address(address)
                .companyEmail("jdoe@perficeint.com")
                .contactEmail("jdoe@gmail.com")
                .birthDate(LocalDate.of(1901,4,20).toString())
                .hireDate(LocalDate.now().toString())
                .build();
        log.info(employee.toString());
        employeeRepository.save(employee);
        assertNotNull(employee.getId());
        //assertEquals(employeeRepository.findAll().size(),1);

    }

}
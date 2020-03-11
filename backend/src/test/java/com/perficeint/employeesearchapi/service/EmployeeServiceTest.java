package com.perficeint.employeesearchapi.service;

import com.perficeint.employeesearchapi.model.Address;
import com.perficeint.employeesearchapi.model.Employee;
import com.perficeint.employeesearchapi.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void getAllEmployees() {
        Address address = Address.builder()
                ._id(UUID.randomUUID())
                .city("Glenview")
                .country("USA")
                .suite("405")
                .street("2555 Victor Ave")
                .region("Central America")
                .postal("60025")
                .build();
        Employee employee1 = Employee.builder()
                .firstName("Lusam")
                .lastName("Debonfils")
                .address(address)
                .companyEmail("lusam@perficeint.com")
                .contactEmail("lusam@gmail.com")
                .birthDate(LocalDate.of(1991,4,20).toString())
                .hireDate(LocalDate.now().toString())
                .build();
        Employee employee2 = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .address(address)
                .companyEmail("jdoe@perficeint.com")
                .contactEmail("jdoe@gmail.com")
                .birthDate(LocalDate.of(1900,2,12).toString())
                .hireDate(LocalDate.now().toString())
                .build();
        Mockito.when(employeeRepository.findAll()).thenReturn(
                Arrays.asList(employee1,employee2)
                //Stream.of(employee1,employee2).collect(Collectors.toList())
        );

        assertEquals(employeeService.getAllEmployees().size(),2);

    }

    @Test
    void createEmployee() {
        Address address = Address.builder()
                ._id(UUID.randomUUID())
                .city("Glenview")
                .country("USA")
                .suite("405")
                .street("2555 Victor Ave")
                .region("Central America")
                .postal("60025")
                .build();
        Employee employee1 = Employee.builder()
                .firstName("Lusam")
                .lastName("Debonfils")
                .address(address)
                .companyEmail("lusam@perficeint.com")
                .contactEmail("lusam@gmail.com")
                .birthDate(LocalDate.of(1991,4,20).toString())
                .hireDate(LocalDate.now().toString())
                .build();

        Mockito.when(employeeRepository.save(employee1)).thenReturn(employee1);
        assertEquals(employeeService.createEmployee(employee1),employee1);
    }

    @Test
    void findEmployeeById() {
        Address address = Address.builder()
                ._id(UUID.randomUUID())
                .city("Glenview")
                .country("USA")
                .suite("405")
                .street("2555 Victor Ave")
                .region("Central America")
                .postal("60025")
                .build();
        Employee employee1 = Employee.builder()
                .id("12345-123-272")
                .firstName("Lusam")
                .lastName("Debonfils")
                .address(address)
                .companyEmail("lusam@perficeint.com")
                .contactEmail("lusam@gmail.com")
                .birthDate(LocalDate.of(1991,4,20).toString())
                .hireDate(LocalDate.now().toString())
                .build();

        Mockito.when(employeeRepository.findById("12345-123-272")).thenReturn(Optional.of(employee1));
        assertEquals(employeeService.findEmployeeById("12345-123-272"),employee1);
    }

    @Test
    void updateEmployeeById() {
        Address address = Address.builder()
                ._id(UUID.randomUUID())
                .city("Glenview")
                .country("USA")
                .suite("405")
                .street("2555 Victor Ave")
                .region("Central America")
                .postal("60025")
                .build();
        Employee employee1 = Employee.builder()
                .id("12345-123-272")
                .firstName("Lusam")
                .lastName("Debonfils")
                .address(address)
                .companyEmail("lusam@perficeint.com")
                .contactEmail("lusam@gmail.com")
                .birthDate(LocalDate.of(1991,4,20).toString())
                .hireDate(LocalDate.now().toString())
                .build();

//        Mockito.when(employeeRepository.save(employee1)).thenReturn(employee1);
//        assertEquals(employeeService.updateEmployeeById("12345-123-272"),employee1);
        employeeService.updateEmployeeById("12345-123-272");
        Mockito.verify(employeeRepository,Mockito.times(1)).findById("12345-123-272");

    }

    @Test
    void deleteEmployeeById() {
        employeeService.deleteEmployeeById("12345-123-272");
        Mockito.verify(employeeRepository,Mockito.times(1)).findById("12345-123-272");

    }
}
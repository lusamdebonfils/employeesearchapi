package com.perficeint.employeesearchapi.controller;

import com.perficeint.employeesearchapi.model.Address;
import com.perficeint.employeesearchapi.model.Employee;
import com.perficeint.employeesearchapi.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeSearchControllerTest {

    @InjectMocks
    private EmployeeSearchController employeeSearchController;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

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
        Mockito.when(employeeService.getAllEmployees()).thenReturn(
                //Arrays.asList(employee1,employee2)
                Stream.of(employee1,employee2).collect(Collectors.toList())
        );

        assertEquals(employeeSearchController.getAllEmployees().getBody().size(),2);
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
        Employee employee = Employee.builder()
                .id("123")
                .firstName("Lusam")
                .lastName("Debonfils")
                .address(address)
                .companyEmail("lusam@perficeint.com")
                .contactEmail("lusam@gmail.com")
                .birthDate(LocalDate.of(1901,4,20).toString())
                .hireDate(LocalDate.now().toString())
                .build();

        assertEquals(employeeSearchController.createEmployee(employee).getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    void getEmployeeByIdIfPresent() {
        Address address = Address.builder()
                ._id(UUID.randomUUID())
                .city("Glenview")
                .country("USA")
                .suite("405")
                .street("2555 Victor Ave")
                .region("Central America")
                .postal("60025")
                .build();
        Employee employee = Employee.builder()
                .id("123")
                .firstName("Lusam")
                .lastName("Debonfils")
                .address(address)
                .companyEmail("lusam@perficeint.com")
                .contactEmail("lusam@gmail.com")
                .birthDate(LocalDate.of(1901,4,20).toString())
                .hireDate(LocalDate.now().toString())
                .build();
        Mockito.when(employeeService.findEmployeeById("123")).thenReturn(employee);
        //assertEquals(employeeSearchController.getEmployeeById("123").getStatusCode(), HttpStatus.OK);
        assertEquals(employeeSearchController.getEmployeeById("123").getBody(), employee);

    }
    @Test
    void testIfEmployeeByIdPresent() {
        Address address = Address.builder()
                ._id(UUID.randomUUID())
                .city("Glenview")
                .country("USA")
                .suite("405")
                .street("2555 Victor Ave")
                .region("Central America")
                .postal("60025")
                .build();
        Employee employee = Employee.builder()
                .id("123")
                .firstName("Lusam")
                .lastName("Debonfils")
                .address(address)
                .companyEmail("lusam@perficeint.com")
                .contactEmail("lusam@gmail.com")
                .birthDate(LocalDate.of(1901,4,20).toString())
                .hireDate(LocalDate.now().toString())
                .build();
        Mockito.when(employeeService.findEmployeeById("123")).thenReturn(employee);
        assertEquals(employeeSearchController.getEmployeeById("123").getStatusCode(), HttpStatus.FOUND);
        assertEquals(employeeSearchController.getEmployeeById("123").getBody(), employee);

    }
    @Test
    void testIfEmployeeByIdNotPresent() {
        Address address = Address.builder()
                ._id(UUID.randomUUID())
                .city("Glenview")
                .country("USA")
                .suite("405")
                .street("2555 Victor Ave")
                .region("Central America")
                .postal("60025")
                .build();
        Employee employee = Employee.builder()
                .id("123")
                .firstName("Lusam")
                .lastName("Debonfils")
                .address(address)
                .companyEmail("lusam@perficeint.com")
                .contactEmail("lusam@gmail.com")
                .birthDate(LocalDate.of(1901,4,20).toString())
                .hireDate(LocalDate.now().toString())
                .build();
        Mockito.when(employeeService.findEmployeeById("123")).thenReturn(null);
        assertEquals(employeeSearchController.getEmployeeById("123").getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(employeeSearchController.getEmployeeById("123").getBody(), "Employee not found");

    }

    @Test
    void updateEmployee() {

    }

    @Test
    void deleteEmployee() {
    }
}
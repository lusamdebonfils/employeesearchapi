package com.perficeint.employeesearchapi.controller;

import com.perficeint.employeesearchapi.model.Employee;
import com.perficeint.employeesearchapi.model.Skill;
import com.perficeint.employeesearchapi.service.IemployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeSearchController {

    private IemployeeService employeeService;

    public EmployeeSearchController(IemployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id){
        if(employeeService.findEmployeeById(id)==null){
            return new ResponseEntity<>("Employee not found",HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(employeeService.findEmployeeById(id),HttpStatus.FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable String id){
        if(employeeService.findEmployeeById(id)==null){
            return new ResponseEntity<>("Employee not found",HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(employeeService.updateEmployeeById(id),HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id){
        if(employeeService.findEmployeeById(id) == null){
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }else{
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<> (employeeService.deleteEmployeeById(id),HttpStatus.OK);
        }
    }
    @GetMapping("/{id}/skills")
    public ResponseEntity<?> getEmployeeSkillsByEmpId(@PathVariable String id){
        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null){
            return new ResponseEntity<> (employee.getSkills(),HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }
    @PostMapping("/{id}/skills")
    public ResponseEntity<?> addEmployeeSkillsByEmpId(@PathVariable String id,@RequestParam Skill skill){
        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null){
            employee.getSkills().add(skill);
            employeeService.createEmployee(employee);
            return new ResponseEntity<> (skill,HttpStatus.OK);
        }
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}/skills/{skillId}")
    public ResponseEntity<?> getEmployeeSkillByEmpIdAndSkillId(@PathVariable String empId,@PathVariable String skillId){
        Employee employee = employeeService.findEmployeeById(empId);
        if (employee != null){
            Skill targetSkill = employee.getSkills().stream().filter((skill)->skill.get_id().equals(skillId)).findAny().orElse(null);
            if (targetSkill != null){
                return new ResponseEntity<>(targetSkill,HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Employee doesnt have that skill", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}/skills/{skillId}")
    public ResponseEntity<?> deleteEmployeeSkillByEmpId(@PathVariable String empId,@PathVariable String skillId){
        Employee employee = employeeService.findEmployeeById(empId);
        if (employee != null){
            List<Skill> skills = employee.getSkills().stream().filter((skill)->!skill.get_id().equals(skillId)).collect(Collectors.toList());
            employee.setSkills(skills);
            employeeService.createEmployee(employee);
            return new ResponseEntity<> ("Updated skills",HttpStatus.OK);
        }
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }


}

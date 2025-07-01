package com.cwc.EmployeeServer.controller;

import com.cwc.EmployeeServer.dto.EmployeeDTO;
import com.cwc.EmployeeServer.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Iterable<EmployeeDTO>> getAllEmployees() {
        Iterable<EmployeeDTO> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.save(employeeDTO);
        return ResponseEntity.status(201).body(createdEmployee);
    }


    @PostMapping("/list")
    public ResponseEntity<Iterable<EmployeeDTO>> createEmployees(@RequestBody @Valid List<EmployeeDTO> employeeDTOs) {
        Iterable<EmployeeDTO> createdEmployees = employeeService.saveAll(employeeDTOs);
        return ResponseEntity.status(201).body(createdEmployees);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.findById(id);
        if (employeeDTO != null) {
            return ResponseEntity.ok(employeeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        if (employeeService.existsById(id)) {
            employeeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //update an employee
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employeeDTO) {
        if (employeeService.existsById(id)) {
            employeeDTO.setId(id);
            EmployeeDTO updatedEmployee = employeeService.update(employeeDTO);
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.update(employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }
    @PutMapping("/list")
    public ResponseEntity<Iterable<EmployeeDTO>> updateEmployees(@RequestBody @Valid List<EmployeeDTO> employeeDTOs) {
        Iterable<EmployeeDTO> updatedEmployees = employeeService.saveAll(employeeDTOs);
        return ResponseEntity.ok(updatedEmployees);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> employeeExists(@PathVariable Long id) {
        boolean exists = employeeService.existsById(id);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/count")
    public ResponseEntity<Long> getEmployeeCount() {
        long count = employeeService.count();
        return ResponseEntity.ok(count);
    }
    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<EmployeeDTO>> findByLastName(@PathVariable String lastName) {
        List<EmployeeDTO> employees = employeeService.findByLastName(lastName);
        return ResponseEntity.ok(employees);
    }
    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<List<EmployeeDTO>> findByFirstName(@PathVariable String firstName) {
        List<EmployeeDTO> employees = employeeService.findByFirstName(firstName);
        return ResponseEntity.ok(employees);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<List<EmployeeDTO>> findByEmail(@PathVariable String email) {
        List<EmployeeDTO> employees = employeeService.findByEmail(email);
        return ResponseEntity.ok(employees);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteEmployee(@RequestBody EmployeeDTO employeeDTO) {
        if (employeeService.existsById(employeeDTO.getId())) {
            employeeService.delete(employeeDTO);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/list")
    public ResponseEntity<Void> deleteAllEmployees(@RequestBody List<EmployeeDTO> employeeDTOS) {
        if (employeeDTOS.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        employeeService.deleteAll(employeeDTOS);
        return ResponseEntity.noContent().build();
    }

}

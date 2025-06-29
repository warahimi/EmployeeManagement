package com.cwc.EmployeeServer.service;

import com.cwc.EmployeeServer.dto.EmployeeDTO;
import com.cwc.EmployeeServer.entities.Employee;
import com.cwc.EmployeeServer.repository.EmployeeRepository;
import com.cwc.EmployeeServer.utilities.Utilities;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = Utilities.employeeDTOToEmoployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return Utilities.employeeToEmployeeDTO(savedEmployee);
    }
    public List<EmployeeDTO> saveAll(List<EmployeeDTO> employeeDTOs) {
        List<Employee> employees = employeeDTOs.stream()
                .map(Utilities::employeeDTOToEmoployee)
                .toList();
        List<Employee> savedEmployees = employeeRepository.saveAll(employees);
        return savedEmployees.stream()
                .map(Utilities::employeeToEmployeeDTO)
                .toList();
    }
    public EmployeeDTO findById(Long id) {
        return employeeRepository.findById(id)
                .map(Utilities::employeeToEmployeeDTO)
                .orElse(null);
    }
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
    public Iterable<EmployeeDTO> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(Utilities::employeeToEmployeeDTO)
                .toList();
    }
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        Employee employee = Utilities.employeeDTOToEmoployee(employeeDTO);
        Employee updatedEmployee = employeeRepository.save(employee);
        return Utilities.employeeToEmployeeDTO(updatedEmployee);
    }
    public boolean existsById(Long id) {
        return employeeRepository.existsById(id);
    }
    public long count() {
        return employeeRepository.count();
    }
    public void deleteAll() {
        employeeRepository.deleteAll();
    }
    public void delete(EmployeeDTO employeeDTO) {
        Employee employee = Utilities.employeeDTOToEmoployee(employeeDTO);
        employeeRepository.delete(employee);
    }
    public void deleteAll(Iterable<EmployeeDTO> employeeDTOS) {
        List<Employee> employees = ((List<EmployeeDTO>) employeeDTOS).stream()
                .map(Utilities::employeeDTOToEmoployee)
                .toList();
        employeeRepository.deleteAll(employees);
    }
    public void flush() {
        employeeRepository.flush();
    }
    public List<EmployeeDTO> findByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName)
                .stream()
                .map(Utilities::employeeToEmployeeDTO)
                .toList();
    }
    public List<EmployeeDTO> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department)
                .stream()
                .map(Utilities::employeeToEmployeeDTO)
                .toList();
    }
    public List<EmployeeDTO> findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName)
                .stream()
                .map(Utilities::employeeToEmployeeDTO)
                .toList();
    }
    public List<EmployeeDTO> findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .stream()
                .map(Utilities::employeeToEmployeeDTO)
                .toList();
    }
    public List<EmployeeDTO> findByDepartmentAndLastName(String department, String lastName) {
        return employeeRepository.findByDepartmentAndLastName(department, lastName)
                .stream()
                .map(Utilities::employeeToEmployeeDTO)
                .toList();
    }

}

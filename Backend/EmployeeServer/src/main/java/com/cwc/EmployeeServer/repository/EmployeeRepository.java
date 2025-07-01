package com.cwc.EmployeeServer.repository;

import com.cwc.EmployeeServer.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);
    List<Employee> findByDepartment(String department);
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByEmail(String email);
    List<Employee> findByDepartmentAndLastName(String department, String lastName);

    //List<Employee> findByDepartment(String department, Pageable pageable);
}

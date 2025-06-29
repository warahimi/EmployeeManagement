package com.cwc.EmployeeServer.utilities;

import com.cwc.EmployeeServer.dto.EmployeeDTO;
import com.cwc.EmployeeServer.entities.Employee;

public class Utilities {
    public static Employee employeeDTOToEmoployee(EmployeeDTO employeeDTO)
    {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setDepartment(employeeDTO.getDepartment());
        return employee;
    }
    public static EmployeeDTO employeeToEmployeeDTO(Employee employee)
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setDepartment(employee.getDepartment());
        return employeeDTO;
    }
}

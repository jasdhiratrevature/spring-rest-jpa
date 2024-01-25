package com.revature.jpa.services;

import com.revature.jpa.exceptions.ResourceNotFoundException;
import com.revature.jpa.model.Employee;
import com.revature.jpa.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return employee;
    }


    public Employee saveEmployee(Employee employee){
       return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long employeeId,                                                    Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmail(employeeDetails.getEmail());
        employee.setName(employeeDetails.getName());
        employee.setPhone(employeeDetails.getPhone());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }


    public Employee deleteEmployee(Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);

        return employee;
    }
}

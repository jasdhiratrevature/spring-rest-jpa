package com.revature.jpa;

import com.revature.jpa.exceptions.ResourceNotFoundException;
import com.revature.jpa.model.Employee;
import com.revature.jpa.repos.EmployeeRepository;
import com.revature.jpa.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    // @Mock is used to create a mock instance of a class or interface.
    // This is useful when you want to test a specific class or method that depends on another class or interface,
    // without actually having to instantiate that dependency.
    @Mock
    EmployeeRepository employeeRepository;

   // @InjectMocks is used to inject the mocks that were created using the @Mock annotation into the target object
   // (in this case, the EmployeeService class).
    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void testCreateEmployee() {
        // given
        Employee employee = new Employee();
        employee.setName("Jasdhir");
        employee.setEmail("jasdhir@mail.com");
        employee.setPhone(123444444);

        when(employeeRepository.save(employee)).thenReturn(employee);

        // when
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // then
        assertEquals(employee, savedEmployee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetEmployeeById() throws ResourceNotFoundException {
        // given
        Long employeeId = 1L;
        Employee employee = new Employee();
        employee.setId(employeeId);
       employee.setName("Jasdhir");
       employee.setEmail("jasdhir@email.com");
       employee.setPhone(1234344444);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        // when
        Employee foundEmployee = employeeService.getEmployeeById(1L);

        // then
        assertEquals(employee, foundEmployee);
        verify(employeeRepository, times(1)).findById(employeeId);
    }
}

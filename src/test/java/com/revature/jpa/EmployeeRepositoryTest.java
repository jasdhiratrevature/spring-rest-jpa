package com.revature.jpa;

import com.revature.jpa.model.Employee;
import com.revature.jpa.repos.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setName("Akash");
        employee.setEmail("Akash@mail.com");
        employee.setPhone(23343344);
       Employee savedEmployee=employeeRepository.save(employee);

        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getName()).isEqualTo("Akash11");
        assertThat(savedEmployee.getEmail()).isEqualTo("Akash@mail.com");
    }
}

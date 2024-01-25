package com.revature.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.jpa.model.Employee;
import com.revature.jpa.repos.EmployeeRepository;
import com.revature.jpa.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Mock
    private RestTemplate restTemplate;

    @Captor
    private ArgumentCaptor<Employee> employeeCaptor;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUserWithMockMvc() throws Exception {
        // Creates a new Employee and sets name , email and phone
        // Configures the behavior of employeeService.saveEmployee() method to return the created employee
        // Sends a POST request to "/employees" with the user object as JSON payload
        // Performs assertions on the response status, content type, and JSON fields
        // Verifies that employeeService.saveEmployee() is called with the captured employee object
        Employee employee = new Employee();
        employee.setId(101L);
        employee.setName("abc");
        employee.setEmail("Abc@mail.com");
        employee.setPhone(232344);

        when(employeeService.saveEmployee(employeeCaptor.capture())).thenReturn(employee);


        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("abc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("Abc@mail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value(232344)
                );


        verify(employeeService).saveEmployee(employeeCaptor.capture());
        assertThat(employeeCaptor.getValue()).isEqualTo(employee);


    }
}
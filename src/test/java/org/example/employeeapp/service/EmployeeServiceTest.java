package org.example.employeeapp.service;

import org.example.employeeapp.model.Employee;
import org.example.employeeapp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringJUnitConfig
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEmployeeById_Success() {
        // Arrange
        int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setEmployeeName("name");
        employee.setEmployeeAge(20);
        employee.setEmployeeSalary(5000);
        employee.setProfileImage("");
        System.out.println("id = " + id);
        System.out.println("employee = " + Optional.of(employee));
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        // Act
        Employee result = employeeService.getEmployeeById(id);

        // Assert
        assertNotNull(result, "Employee should not be null");
        assertEquals(id, result.getId(), "Employee ID should match");
        assertEquals(5000, result.getEmployeeSalary(), "Employee salary should match");
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        // Arrange
        int id = 2;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            employeeService.getEmployeeById(id);
        }, "Employee with ID 2 not found");

        assertTrue(thrown.getMessage().contains("Employee with ID 2 not found"));
    }
}

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

/**
 * Test class for {@link EmployeeService}.
 * <p>
 * This class contains tests for the {@link EmployeeService} class, specifically testing the
 * functionality of retrieving employees by their ID.
 * <p>
 * The tests use Mockito for mocking dependencies and JUnit 5 for the testing framework.
 */
@SpringJUnitConfig
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeServiceTest {

    /**
     * Mock of the {@link EmployeeRepository} used to simulate interactions with the database.
     */
    @Mock
    private EmployeeRepository employeeRepository;

    /**
     * Instance of {@link EmployeeService} to be tested. Mockito will inject the mocks into this instance.
     */
    @InjectMocks
    private EmployeeService employeeService;

    /**
     * Setup method that initializes Mockito annotations before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for successfully retrieving an employee by their ID.
     * <p>
     * This test verifies that the {@link EmployeeService#getEmployeeById(int)} method correctly returns
     * an employee when the ID is found in the mock repository.
     */
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

    /**
     * Test case for handling the scenario where an employee is not found by their ID.
     * <p>
     * This test verifies that the {@link EmployeeService#getEmployeeById(int)} method throws a
     * {@link RuntimeException} with the appropriate message when the ID is not found in the mock repository.
     */
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

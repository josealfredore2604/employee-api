package org.example.employeeapp.service;

import lombok.Getter;
import lombok.Setter;
import org.example.employeeapp.model.Employee;
import org.example.employeeapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing employee-related operations.
 */
@Service
public class EmployeeService {
    private static final String BASE_URL = "http://dummy.restapiexample.com/api/v1";

    private final RestTemplate restTemplate;
    private final EmployeeRepository employeeRepository;

    /**
     * Constructor for injecting the RestTemplate and EmployeeRepository.
     *
     * @param restTemplate The RestTemplate used to make API calls.
     * @param employeeRepository The repository used for employee data management.
     */
    @Autowired
    public EmployeeService(RestTemplate restTemplate, EmployeeRepository employeeRepository) {
        this.restTemplate = restTemplate;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Retrieve all employees from the database.
     *
     * @return A list of all employees.
     */
    public List<Employee> getEmployees() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            // Log the exception (use a logging framework like SLF4J)
            throw new RuntimeException("Error retrieving employees from the database", e);
        }
    }

    /**
     * Retrieve an employee by their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The employee with the specified ID.
     */
    public Employee getEmployeeById(int id) {
        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isPresent()) {
                return employee.get();
            } else {
                throw new RuntimeException("Employee with ID " + id + " not found");
            }
        } catch (Exception e) {
            // Log the exception
            throw new RuntimeException("Error retrieving employee with ID " + id, e);
        }
    }

    /**
     * Compute the annual salary for an employee by their ID.
     *
     * @param id The ID of the employee whose salary is to be computed.
     * @return The annual salary of the employee.
     */
    public double computeAnnualSalary(int id) {
        try {
            Employee employee = getEmployeeById(id);
            return employee.getEmployeeSalary() * 12;
        } catch (RuntimeException e) {
            // Handle not found case or other issues
            throw new RuntimeException("Error computing annual salary for employee with ID " + id, e);
        }
    }

    /**
     * Fetch and save all employees from the external API.
     *
     * @throws RuntimeException if an error occurs during the fetch or save process.
     */
    public void fetchAndSaveAllEmployees() {
        String url = BASE_URL + "/employees";
        try {
            EmployeeResponse response = restTemplate.getForObject(url, EmployeeResponse.class);
            if (response != null && response.getData() != null) {
                List<Employee> employees = Arrays.asList(response.getData());
                employeeRepository.saveAll(employees);
            } else {
                throw new RuntimeException("No employees data received from API");
            }
        } catch (RestClientException e) {
            // Handle issues with API request
            throw new RuntimeException("Error fetching employees from API", e);
        } catch (Exception e) {
            // Handle other unexpected issues
            throw new RuntimeException("Error saving employees to database", e);
        }
    }

    /**
     * Inner class to handle JSON response from the API.
     */
    @Getter
    @Setter
    private static class EmployeeResponse {
        private String status;
        private Employee[] data;
        private String message;
    }
}

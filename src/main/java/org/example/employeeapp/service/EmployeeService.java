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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Retrieve all employees from the database.
     *
     * @return A list of all employees.
     */
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Retrieve an employee by their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The employee with the specified ID.
     * @throws RuntimeException if the employee is not found.
     */
    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RuntimeException("Employee with ID " + id + " not found");
        }
    }

    /**
     * Compute the annual salary for an employee by their ID.
     *
     * @param id The ID of the employee whose salary is to be computed.
     * @return The annual salary of the employee.
     * @throws RuntimeException if the employee is not found.
     */
    public double computeAnnualSalary(int id) {
        Employee employee = getEmployeeById(id);
        return employee.getEmployeeSalary() * 12;
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
            throw new RuntimeException("Error fetching employees from API", e);
        } catch (Exception e) {
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
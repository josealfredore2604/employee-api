package org.example.employeeapp.controller;

import org.example.employeeapp.model.Employee;
import org.example.employeeapp.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * REST controller for managing employee-related operations.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * Constructor for injecting the EmployeeService.
     *
     * @param employeeService The service used to manage employee data.
     */
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Endpoint to get all employees.
     *
     * @return A list of all employees.
     */
    @Operation(summary = "Get all employees", description = "Retrieve the list of all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of employees"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getEmployees() {
        try {
            List<Employee> employees = employeeService.getEmployees();
            return ResponseEntity.ok(employees);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving employees: " + e.getMessage());
        }
    }

    /**
     * Endpoint to get an employee by their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The employee with the specified ID.
     */
    @Operation(summary = "Get employee by ID", description = "Retrieve an employee by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(
            @Parameter(description = "ID of the employee to retrieve") @PathVariable int id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            if (employee != null) {
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Employee with ID " + id + " not found");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving employee with ID " + id + ": " + e.getMessage());
        }
    }

    /**
     * Endpoint to compute the annual salary for an employee by their ID.
     *
     * @param id The ID of the employee whose salary is to be computed.
     * @return The annual salary of the employee.
     */
    @Operation(summary = "Compute annual salary", description = "Compute the annual salary for an employee by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully computed the annual salary"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/annual-salary/{id}")
    public ResponseEntity<?> computeAnnualSalary(
            @Parameter(description = "ID of the employee whose salary is to be computed") @PathVariable int id) {
        try {
            double annualSalary = employeeService.computeAnnualSalary(id);
            return ResponseEntity.ok(annualSalary);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error computing annual salary for employee with ID " + id + ": " + e.getMessage());
        }
    }

    /**
     * Endpoint to fetch and save all employees from the external API.
     *
     * @return A message indicating success or failure.
     */
    @Operation(summary = "Fetch and save all employees", description = "Fetch and save all employees from the external API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched and saved employees"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/fetch")
    public ResponseEntity<String> fetchAndSaveAllEmployees() {
        try {
            employeeService.fetchAndSaveAllEmployees();
            return ResponseEntity.ok("Employees fetched and saved successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching and saving employees: " + e.getMessage());
        }
    }
}

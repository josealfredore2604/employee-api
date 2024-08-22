package org.example.employeeapp.repository;

import org.example.employeeapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on `Employee` entities.
 *
 * Extends JpaRepository to provide basic CRUD operations and additional JPA functionalities.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // JpaRepository provides methods such as save, findAll, findById, deleteById, etc.
}

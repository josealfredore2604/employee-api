# Employee Management API

## Overview

The **Employee Management API** is a RESTful service for managing employee data. It provides endpoints for retrieving, creating, and processing employee information. This application is built using Spring Boot and utilizes an in-memory database for simplicity and ease of testing.

## Technologies Used

- **Spring Boot**: Framework for building Java-based applications with minimal configuration.
- **Spring Data JPA**: Simplifies data access using JPA and Hibernate.
- **Lombok**: Reduces boilerplate code by automatically generating getters, setters, and other common methods.
- **JSON Processing (javax.json)**: Provides support for processing JSON data.
- **H2 Database**: An in-memory database used for testing and development.
- **Springdoc OpenAPI**: Provides automatic generation of OpenAPI documentation and Swagger UI for the API.
- **JUnit 5**: Framework for writing and running tests.
- **Maven**: Dependency management and build automation.

## Dependencies

The application includes the following dependencies:

- **Spring Boot Starter Web**: For building web applications and RESTful APIs.
- **Spring Boot Starter Data JPA**: For integrating JPA and Hibernate.
- **Lombok**: For reducing boilerplate code (e.g., getters, setters).
- **JUnit 5**: For unit testing the application.
- **JSON Processing**: For handling JSON data (javax.json).
- **Spring Boot Starter Test**: For testing support in Spring Boot applications.
- **H2 Database**: For an in-memory database during runtime.
- **Swagger UI**: For API documentation and interactive API testing.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8.1 or higher

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/josealfredore2604/employee-api.git
   ```

2. **Navigate to the Project Directory**

   ```bash
   cd employee-app
   ```

### Running the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```

By default, the application will start on port `8080`. You can access the API documentation through Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

### In-Memory Database

This application uses the H2 database in-memory mode for simplicity. The database is automatically initialized with some default data. You can access the H2 console to interact with the database at:

```
http://localhost:8080/h2-console
```

**Note**: The JDBC URL for the H2 console is pre-configured as `jdbc:h2:mem:db`. You can use the default username `sa` and leave the password blank.

### API Endpoints

#### Get All Employees

- **URL**: `/employees`
- **Method**: `GET`
- **Description**: Retrieve a list of all employees.
- **Response**: `200 OK` with a list of employees or `500 Internal Server Error` if an error occurs.

#### Get Employee by ID

- **URL**: `/employees/{id}`
- **Method**: `GET`
- **Description**: Retrieve an employee by their ID.
- **Parameters**:
  - `id` (path variable): The ID of the employee.
- **Response**:
  - `200 OK` with employee details if found.
  - `404 Not Found` if the employee does not exist.
  - `500 Internal Server Error` if an error occurs.

#### Compute Annual Salary

- **URL**: `/employees/annual-salary/{id}`
- **Method**: `GET`
- **Description**: Compute the annual salary for an employee by their ID.
- **Parameters**:
  - `id` (path variable): The ID of the employee.
- **Response**:
  - `200 OK` with the annual salary.
  - `500 Internal Server Error` if an error occurs.

#### Fetch and Save All Employees

- **URL**: `/employees/fetch`
- **Method**: `POST`
- **Description**: Fetch and save all employees from an external API.
- **Response**:
  - `200 OK` with a success message.
  - `500 Internal Server Error` if an error occurs.

## Application Configuration

The application configuration is managed via the `application.properties` file located in the `src/main/resources` directory. Here you can configure database settings, logging, and other properties.

## Packaging and Running as a WAR

To package the application as a WAR file and run it, follow these steps:

1. **Modify the `pom.xml` file**: Ensure the file contains the following packaging configuration:

    ```xml
    <packaging>war</packaging>
    ```

2. **Package the Application**: Use Maven to create the WAR file:

    ```bash
    mvn clean package
    ```

   This will generate a WAR file in the `target` directory.

3. **Run the WAR File**: Use the following command to run the WAR file:

    ```bash
    java -jar target/employee-app.war
    ```

   Replace `employee-app.war` with the actual name of the generated WAR file.

## Acknowledgements

- **Spring Boot**: For providing a powerful framework for building Java applications.
- **Springdoc OpenAPI**: For automatic generation of API documentation and Swagger UI.
- **Lombok**: For reducing boilerplate code.
- **JSON Processing (javax.json)**: For handling JSON data.

## Contact

For any questions or issues, please contact [josealfredore200326@gmail.com](mailto:josealfredore200326@gmail.com).
```
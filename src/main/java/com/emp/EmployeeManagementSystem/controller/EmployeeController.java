package com.emp.EmployeeManagementSystem.controller;

import com.emp.EmployeeManagementSystem.dto.EmployeeDTO;
import com.emp.EmployeeManagementSystem.entity.Department;
import com.emp.EmployeeManagementSystem.entity.Designation;
import com.emp.EmployeeManagementSystem.entity.Employee;
import com.emp.EmployeeManagementSystem.entity.Response;
import com.emp.EmployeeManagementSystem.service.DepartmentService;
import com.emp.EmployeeManagementSystem.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Employee in EmployeeManagementSystem",
        description = "REST APIs to CREATE, READ, UPDATE and DELETE Employee details"
)
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "Add New Employee", description = "Create a new employee record")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/add")
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO saved = employeeService.saveEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Get Employees by Designation", description = "Fetch all employees matching a specific designation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of employees by designation")
    })
    @GetMapping("/designation")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDesignation(@RequestParam String designation) {
        Designation enumDesignation = Designation.valueOf(designation.toUpperCase());
        List<EmployeeDTO> employees = employeeService.getEmployeesByDesignation(enumDesignation);
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get Employees by Salary Range", description = "Fetch employees whose salaries fall within the provided range")
    @ApiResponse(responseCode = "200", description = "List of employees in the specified salary range")
    @GetMapping("/search/salary")
    public ResponseEntity<List<EmployeeDTO>> searchBySalaryRange(
            @RequestParam int minSalary,
            @RequestParam int maxSalary) {
        List<EmployeeDTO> employees = employeeService.searchBySalaryRange(minSalary, maxSalary);
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get Employees by Department", description = "Retrieve all employees belonging to a specific department")
    @ApiResponse(responseCode = "200", description = "List of employees in the department")
    @GetMapping("/search/department")
    public ResponseEntity<List<EmployeeDTO>> findEmployeesByDepartment(@RequestParam String departmentName) {
        List<EmployeeDTO> employeeDTOList = employeeService.findEmployeesByDepartment(departmentName);
        return ResponseEntity.ok(employeeDTOList);
    }

    @Operation(summary = "Get All Employees", description = "Fetch all employees with optional pagination")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of all employees"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllEmployees(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        try {
            List<EmployeeDTO> employeeList = employeeService.getAll(page, size);
            Response response = new Response("List of Employees", employeeList, false);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response errorResponse = new Response("Failed to retrieve employees", e.getMessage(), true);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Operation(summary = "Update Employee by ID", description = "Update an existing employee's details using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

    @Operation(summary = "Delete Employee by ID", description = "Remove an employee from the system using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully with ID: " + id);
    }

    @Operation(summary = "Search Employees using Criteria API", description = "Search by optional filters: designation, department, salary range")
    @ApiResponse(responseCode = "200", description = "Filtered employee list")
    @GetMapping("/search/criteria")
    public ResponseEntity<List<EmployeeDTO>> searchEmployeesByCriteria(
            @RequestParam(required = false) String designation,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) Integer minSalary,
            @RequestParam(required = false) Integer maxSalary
    ) {
        List<EmployeeDTO> employees = employeeService.searchEmployees(designation, departmentName, minSalary, maxSalary);
        return ResponseEntity.ok(employees);
    }



}

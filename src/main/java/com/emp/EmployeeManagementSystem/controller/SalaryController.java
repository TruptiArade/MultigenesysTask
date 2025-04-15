package com.emp.EmployeeManagementSystem.controller;

import com.emp.EmployeeManagementSystem.dto.EmployeeDTO;
import com.emp.EmployeeManagementSystem.dto.SalaryDTO;
import com.emp.EmployeeManagementSystem.service.EmployeeService;
import com.emp.EmployeeManagementSystem.service.SalaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Salary Management REST APIs",
        description = "APIs to add, retrieve, update, and delete salary records"
)
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @Operation(summary = "Add New Salary", description = "Create a new salary record")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Salary created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @PostMapping("/add")
    public ResponseEntity<SalaryDTO> saveSalary(@Valid @RequestBody SalaryDTO salaryDTO) {
        SalaryDTO savedSalary = salaryService.saveSalary(salaryDTO);
        return ResponseEntity.status(201).body(savedSalary);
    }

    @Operation(summary = "Get All Salaries", description = "Retrieve all available salary records")
    @ApiResponse(responseCode = "200", description = "List of salaries retrieved successfully")
    @GetMapping("/all")
    public ResponseEntity<List<SalaryDTO>> getAllSalaries() {
        List<SalaryDTO> salaries = salaryService.getAllSalaries();
        return ResponseEntity.ok(salaries);
    }

    @Operation(summary = "Update Salary by ID", description = "Update a salary record using its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Salary updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or data"),
            @ApiResponse(responseCode = "404", description = "Salary not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<SalaryDTO> updateSalary(
            @PathVariable int id,
            @Valid @RequestBody SalaryDTO salaryDTO) {
        SalaryDTO updatedSalary = salaryService.updateSalary(id, salaryDTO);
        return ResponseEntity.ok(updatedSalary);
    }

    @Operation(summary = "Delete Salary by ID", description = "Delete a salary record by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Salary deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Salary not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSalary(@PathVariable int id) {
        salaryService.deleteSalary(id);
        return ResponseEntity.ok("Salary deleted successfully with ID: " + id);
    }
}

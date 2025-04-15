package com.emp.EmployeeManagementSystem.controller;

import com.emp.EmployeeManagementSystem.dto.DepartmentDTO;
import com.emp.EmployeeManagementSystem.dto.ErrorResponseDto;
import com.emp.EmployeeManagementSystem.dto.ResponseDto;
import com.emp.EmployeeManagementSystem.entity.Department;
import com.emp.EmployeeManagementSystem.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Department in Employee Management System",
        description = "REST APIs to Create, Read, Update, and Delete Department data"
)
@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
@Validated
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Add new department", description = "Add a new department to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Department created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/add")
    public ResponseEntity<DepartmentDTO> saveDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO saved = departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Get department by ID", description = "Fetch department details using department ID")
    @GetMapping("/search/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable @Min(1) int id) {
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @Operation(summary = "Get all departments", description = "Fetch all available departments")
    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @Operation(summary = "Update department", description = "Update department details by ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable int id,
                                                          @Valid @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO updated = departmentService.updateDepartment(id, departmentDTO);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete department", description = "Delete a department by its ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(new ResponseDto("200", "Department deleted successfully with ID: " + id));
    }
}

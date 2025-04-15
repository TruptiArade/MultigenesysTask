package com.emp.EmployeeManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Department", description = "Schema to hold Department details")
public class DepartmentDTO {

    @Schema(description = "Unique ID of the department", example = "1")
    private int did;

    @NotBlank(message = "Department name cannot be blank")
    @Size(min = 2, max = 100, message = "Department name must be between 2 and 100 characters")
    @Schema(description = "Name of the department", example = "Human Resources")
    private String dname;

    @NotBlank(message = "Location cannot be blank")
    @Size(min = 2, max = 100, message = "Location must be between 2 and 100 characters")
    @Schema(description = "Location of the department", example = "New York")
    private String location;
}

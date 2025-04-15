package com.emp.EmployeeManagementSystem.dto;

import com.emp.EmployeeManagementSystem.entity.Designation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "Employee", description = "Schema to hold Employee details")
public class EmployeeDTO {

    @Schema(description = "Unique ID of the employee", example = "101")
    private int eid;

    @NotBlank(message = "Employee name cannot be blank")
    @Size(min = 2, max = 100, message = "Employee name should be between 2 and 100 characters")
    @Schema(description = "Name of the employee", example = "John Doe")
    private String ename;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Designation cannot be null")
    @Schema(description = "Designation of the employee", example = "SOFTWARE_ENGINEER")
    private Designation designation;

    @NotNull(message = "Joining date cannot be null")
    @Past(message = "Joining date must be in the past")
    @Schema(description = "Joining date of the employee", example = "2020-01-15")
    private Date joiningDate;

    @NotNull(message = "Salary cannot be null")
    @Schema(description = "Salary details of the employee")
    private SalaryDTO salary;

    @NotNull(message = "Department cannot be null")
    @Schema(description = "Department to which the employee belongs")
    private DepartmentDTO department;
}

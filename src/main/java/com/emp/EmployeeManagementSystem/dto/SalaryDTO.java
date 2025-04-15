package com.emp.EmployeeManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "Salary", description = "Schema to hold Salary details")
public class SalaryDTO {

    @Schema(description = "Unique ID of the salary entry", example = "1")
    private int sid;

    @NotNull(message = "Salary amount cannot be null")
    @Min(value = 1000, message = "Salary amount must be at least 1000")
    @Schema(description = "Salary amount assigned to the employee", example = "50000")
    private int amount;
}

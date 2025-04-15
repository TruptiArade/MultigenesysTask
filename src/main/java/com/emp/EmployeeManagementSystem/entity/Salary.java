package com.emp.EmployeeManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sid;

    @NotNull(message = "Salary amount cannot be null")
    @Min(value = 1000, message = "Salary amount must be at least 1000")
    private Integer amount;
}

package com.emp.EmployeeManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int did;

    @NotBlank(message = "Department name cannot be blank")
    @Size(min = 2, max = 100, message = "Department name must be between 2 and 100 characters")
    private String dname;

    @NotBlank(message = "Location cannot be blank")
    @Size(min = 2, max = 100, message = "Location must be between 2 and 100 characters")
    private String location;
}

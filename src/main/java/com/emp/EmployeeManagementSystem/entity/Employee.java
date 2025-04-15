package com.emp.EmployeeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eid;

    @NotBlank(message = "Employee name cannot be blank") // Ensure employee name is not empty
    @Size(min = 2, max = 100, message = "Employee name should be between 2 and 100 characters") // Ensure name length
    private String ename;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Designation cannot be null") // Ensure designation is provided
    private Designation designation;

    @NotNull(message = "Joining date cannot be null") // Ensure joining date is not null
    @Past(message = "Joining date must be in the past") // Ensure joining date is a past date
    private Date joiningDate;

    @ManyToOne
    @JoinColumn(name = "salary_id")
    @NotNull(message = "Salary cannot be null") // Ensure salary is not null
    private Salary salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @NotNull(message = "Department cannot be null") // Ensure department is not null
    private Department department;
}

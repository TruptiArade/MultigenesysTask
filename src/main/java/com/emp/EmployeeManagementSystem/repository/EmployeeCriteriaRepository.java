package com.emp.EmployeeManagementSystem.repository;

import com.emp.EmployeeManagementSystem.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCriteriaRepository
{
    List<Employee> searchEmployees(String designation, String departmentName, Integer minSalary, Integer maxSalary);

}

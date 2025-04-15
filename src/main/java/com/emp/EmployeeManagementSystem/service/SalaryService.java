package com.emp.EmployeeManagementSystem.service;

import com.emp.EmployeeManagementSystem.dto.DepartmentDTO;
import com.emp.EmployeeManagementSystem.dto.SalaryDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SalaryService
{
    SalaryDTO saveSalary(SalaryDTO salaryDTO);
    List<SalaryDTO> getAllSalaries();
    SalaryDTO updateSalary(int id, SalaryDTO salaryDTO);
    void deleteSalary(int id);

}

package com.emp.EmployeeManagementSystem.service;

import com.emp.EmployeeManagementSystem.dto.DepartmentDTO;
import com.emp.EmployeeManagementSystem.dto.EmployeeDTO;
import com.emp.EmployeeManagementSystem.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DepartmentService
{
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);
    Department getDepartmentById(int id);
    List<Department> getAllDepartments();
    DepartmentDTO updateDepartment(int id, DepartmentDTO departmentDTO);
    void deleteDepartment(int id);


}

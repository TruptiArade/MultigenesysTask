package com.emp.EmployeeManagementSystem.service;

import com.emp.EmployeeManagementSystem.dto.EmployeeDTO;
import com.emp.EmployeeManagementSystem.entity.Department;
import com.emp.EmployeeManagementSystem.entity.Designation;
import com.emp.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployeeService
{


    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getEmployeesByDesignation(Designation designation);

    List<EmployeeDTO> searchBySalaryRange(@Param("minSalary") int minSalary, @Param("maxSalary") int maxSalary);

    List<EmployeeDTO> findEmployeesByDepartment(String dname);

    List<EmployeeDTO> getAll(Integer page, Integer size);

    EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO);

    void deleteEmployee(int id);

    List<EmployeeDTO> searchEmployees(String designation, String departmentName, Integer minSalary, Integer maxSalary);



}

package com.emp.EmployeeManagementSystem.repository;

import com.emp.EmployeeManagementSystem.dto.EmployeeDTO;
import com.emp.EmployeeManagementSystem.entity.Designation;
import com.emp.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByDesignation(Designation designation);

    @Query("SELECT e FROM Employee e WHERE e.salary.amount BETWEEN :minSalary AND :maxSalary")
    List<Employee> findEmployeesBySalaryRange(@Param("minSalary") int minSalary, @Param("maxSalary") int maxSalary);

    List<Employee> findByDepartment_Dname(String dname);
}


package com.emp.EmployeeManagementSystem.repository;

import com.emp.EmployeeManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DepartmentRepository extends JpaRepository<Department ,Integer>
{
    // DepartmentRepository.java
    Optional<Department> findByDname(String dname);



}

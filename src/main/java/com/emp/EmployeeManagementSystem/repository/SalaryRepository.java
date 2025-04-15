package com.emp.EmployeeManagementSystem.repository;

import com.emp.EmployeeManagementSystem.entity.Salary;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryRepository  extends JpaRepository<Salary,Integer>
{
    // SalaryRepository.java
    Optional<Salary> findByAmount(int amount);

}

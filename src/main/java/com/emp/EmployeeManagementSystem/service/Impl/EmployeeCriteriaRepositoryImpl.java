// EmployeeCriteriaRepositoryImpl.java
package com.emp.EmployeeManagementSystem.service.Impl;

import com.emp.EmployeeManagementSystem.entity.Employee;
import com.emp.EmployeeManagementSystem.repository.EmployeeCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeCriteriaRepositoryImpl implements EmployeeCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> searchEmployees(String designation, String departmentName, Integer minSalary, Integer maxSalary) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        // Filter by designation
        if (designation != null && !designation.isEmpty()) {
            predicates.add(cb.equal(employeeRoot.get("designation"), designation.toUpperCase()));
        }

        // Filter by department name
        if (departmentName != null && !departmentName.isEmpty()) {
            predicates.add(cb.equal(employeeRoot.get("department").get("dname"), departmentName));
        }

        // Filter by salary range
        if (minSalary != null && maxSalary != null) {
            predicates.add(cb.between(employeeRoot.get("salary").get("amount"), minSalary, maxSalary));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }
}

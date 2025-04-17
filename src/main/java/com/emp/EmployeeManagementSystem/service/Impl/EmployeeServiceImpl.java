package com.emp.EmployeeManagementSystem.service.Impl;

import com.emp.EmployeeManagementSystem.customexception.ResourseNotFoundException;
import com.emp.EmployeeManagementSystem.dto.EmployeeDTO;
import com.emp.EmployeeManagementSystem.dto.SalaryDTO;
import com.emp.EmployeeManagementSystem.entity.Department;
import com.emp.EmployeeManagementSystem.entity.Designation;
import com.emp.EmployeeManagementSystem.entity.Employee;
import com.emp.EmployeeManagementSystem.entity.Salary;
import com.emp.EmployeeManagementSystem.repository.DepartmentRepository;
import com.emp.EmployeeManagementSystem.repository.EmployeeCriteriaRepository;
import com.emp.EmployeeManagementSystem.repository.EmployeeRepository;
import com.emp.EmployeeManagementSystem.repository.SalaryRepository;
import com.emp.EmployeeManagementSystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeCriteriaRepository employeeCriteriaRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private ModelMapper modelMapper;



   @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO)
    {
        Employee e = modelMapper.map(employeeDTO, Employee.class);
        Salary salary = salaryRepository.findById(employeeDTO.getSalary().getSid())
                .orElseThrow(() -> new ResourseNotFoundException("Salary not found with ID"));
        e.setSalary(salary);


        Department department = departmentRepository.findById(employeeDTO.getDepartment().getDid())
                .orElseThrow(() -> new ResourseNotFoundException("Department not found with ID"));
        e.setDepartment(department);

        Employee savedemployee = employeeRepository.save(e);
        return modelMapper.map(savedemployee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDesignation(Designation designation) {
        List<Employee> employees = employeeRepository.findByDesignation(designation);

        if (employees.isEmpty()) {
            throw new ResourseNotFoundException("No employees found with designation: " + designation);
        }

        return employees.stream()
                .map(emp -> modelMapper.map(emp, EmployeeDTO.class))
                .toList();
    }



    @Override
    public List<EmployeeDTO> searchBySalaryRange(int minSalary, int maxSalary) {
        List<Employee> searchSalaryRange = employeeRepository.findEmployeesBySalaryRange(minSalary, maxSalary);

        if (searchSalaryRange.isEmpty()) {
            throw new ResourseNotFoundException("No employees found in salary range: " + minSalary + " - " + maxSalary);
        }


        return searchSalaryRange.stream()
                .map(e -> modelMapper.map(e, EmployeeDTO.class))
                .toList();
    }




    @Override
    public List<EmployeeDTO> findEmployeesByDepartment(String dname) {
        List<Employee> employees = employeeRepository.findByDepartment_Dname(dname);

        if (employees.isEmpty()) {
            throw new ResourseNotFoundException("No employees found in department: " + dname);
        }

        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .toList();
    }

    @Override
    public List<EmployeeDTO> getAll(Integer page, Integer size)
    {
        int pageNumber = (page == null || page < 1) ? 1 : page;
        int pageSize = (size == null || size <= 0) ? 10 : size;

        PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee employee : employeePage.getContent())
        {
            EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

            employeeDTOList.add(employeeDTO);
        }

        return employeeDTOList;
    }

    @Override
    public EmployeeDTO updateEmployee(int id, @Valid EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Employee not found with ID: " + id));

        // Update basic fields
        existingEmployee.setEname(employeeDTO.getEname());
        existingEmployee.setDesignation(employeeDTO.getDesignation());


        // Update department
        if (employeeDTO.getDepartment() != null) {
            Department department = departmentRepository.findById(employeeDTO.getDepartment().getDid())
                    .orElseThrow(() -> new ResourseNotFoundException("Department not found with ID"));
            existingEmployee.setDepartment(department);
        }

        // Update salary
        if (employeeDTO.getSalary() != null) {
            Salary salary = salaryRepository.findById(employeeDTO.getSalary().getSid())
                    .orElseThrow(() -> new ResourseNotFoundException("Salary not found with ID"));
            existingEmployee.setSalary(salary);
        }

        Employee updated = employeeRepository.save(existingEmployee);
        return modelMapper.map(updated, EmployeeDTO.class);
    }

    @Override
    public void deleteEmployee(int id) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Employee not found with ID: " + id));
        employeeRepository.delete(emp);
    }

    @Override
    public List<EmployeeDTO> searchEmployees(String designation, String departmentName, Integer minSalary, Integer maxSalary) {
        List<Employee> employees = employeeCriteriaRepository.searchEmployees(designation, departmentName, minSalary, maxSalary);

        if (employees.isEmpty()) {
            throw new ResourseNotFoundException("No employees found with the provided filters");
        }

        return employees.stream()
                .map(e -> modelMapper.map(e, EmployeeDTO.class))
                .toList();
    }


}

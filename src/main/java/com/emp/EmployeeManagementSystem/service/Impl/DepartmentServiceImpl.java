package com.emp.EmployeeManagementSystem.service.Impl;

import com.emp.EmployeeManagementSystem.customexception.DepartmentAlreadyExistsException;
import com.emp.EmployeeManagementSystem.customexception.ResourseNotFoundException;
import com.emp.EmployeeManagementSystem.dto.DepartmentDTO;
import com.emp.EmployeeManagementSystem.dto.EmployeeDTO;
import com.emp.EmployeeManagementSystem.dto.SalaryDTO;
import com.emp.EmployeeManagementSystem.entity.Department;
import com.emp.EmployeeManagementSystem.entity.Employee;
import com.emp.EmployeeManagementSystem.entity.Salary;
import com.emp.EmployeeManagementSystem.repository.DepartmentRepository;
import com.emp.EmployeeManagementSystem.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService
{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        // Check if department already exists by name
        Optional<Department> existingDepartment = departmentRepository.findByDname(departmentDTO.getDname());

        if (existingDepartment.isPresent()) {
            throw new DepartmentAlreadyExistsException("Department with name " + departmentDTO.getDname() + " already exists.");
        }

        // If department does not exist, proceed with saving the new department
        Department department = modelMapper.map(departmentDTO, Department.class);
        Department saved = departmentRepository.save(department);

        return modelMapper.map(saved, DepartmentDTO.class);
    }


    @Override
    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Department not found with id " + id));
    }


    @Override
    public List<Department> getAllDepartments()
    {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentDTO updateDepartment(int id, DepartmentDTO departmentDTO) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Department not found with ID: " + id));

        existingDepartment.setDname(departmentDTO.getDname());
        existingDepartment.setLocation(departmentDTO.getLocation());

        Department updated = departmentRepository.save(existingDepartment);
        return modelMapper.map(updated, DepartmentDTO.class);
    }

    @Override
    public void deleteDepartment(int id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Department not found with ID: " + id));

        departmentRepository.delete(department);
    }


}

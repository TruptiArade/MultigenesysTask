package com.emp.EmployeeManagementSystem.service.Impl;

import com.emp.EmployeeManagementSystem.customexception.ResourseNotFoundException;
import com.emp.EmployeeManagementSystem.customexception.SalaryAlreadyExistsException;
import com.emp.EmployeeManagementSystem.dto.EmployeeDTO;
import com.emp.EmployeeManagementSystem.dto.SalaryDTO;
import com.emp.EmployeeManagementSystem.entity.Employee;
import com.emp.EmployeeManagementSystem.entity.Salary;
import com.emp.EmployeeManagementSystem.repository.EmployeeRepository;
import com.emp.EmployeeManagementSystem.repository.SalaryRepository;
import com.emp.EmployeeManagementSystem.service.SalaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService
{
    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SalaryDTO saveSalary(SalaryDTO salaryDTO)
    {
        // Check if salary already exists by amount
        Optional<Salary> existingSalary = salaryRepository.findByAmount(salaryDTO.getAmount());

        if (existingSalary.isPresent()) {
            throw new SalaryAlreadyExistsException("Salary with amount " + salaryDTO.getAmount() + " already exists.");
        }

        // If salary does not exist, proceed with saving the new salary
        Salary salary = modelMapper.map(salaryDTO, Salary.class);
        Salary saved = salaryRepository.save(salary);

        return modelMapper.map(saved, SalaryDTO.class);
    }


    @Override
    public List<SalaryDTO> getAllSalaries()
    {
        List<Salary> salaries = salaryRepository.findAll();

        return salaries.stream()
                .map(salary -> modelMapper.map(salary, SalaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SalaryDTO updateSalary(int id, SalaryDTO salaryDTO) {
        Salary existingSalary = salaryRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Salary not found with ID: " + id));

        existingSalary.setAmount(salaryDTO.getAmount());
        Salary updated = salaryRepository.save(existingSalary);
        return modelMapper.map(updated, SalaryDTO.class);
    }

    @Override
    public void deleteSalary(int id) {
        Salary salary = salaryRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Salary not found with ID: " + id));

        salaryRepository.delete(salary);
    }



}

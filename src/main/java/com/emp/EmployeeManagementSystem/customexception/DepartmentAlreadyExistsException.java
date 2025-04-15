package com.emp.EmployeeManagementSystem.customexception;

public class DepartmentAlreadyExistsException extends RuntimeException
{
    public DepartmentAlreadyExistsException(String message)
    {
        super(message);
    }
}

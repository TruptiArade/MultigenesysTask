package com.emp.EmployeeManagementSystem.customexception;

public class SalaryAlreadyExistsException extends RuntimeException
{
    public  SalaryAlreadyExistsException (String message)
    {
        super(message);
    }
}

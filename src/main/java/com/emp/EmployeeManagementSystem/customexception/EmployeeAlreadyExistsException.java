package com.emp.EmployeeManagementSystem.customexception;

public class EmployeeAlreadyExistsException extends RuntimeException
{
    public  EmployeeAlreadyExistsException (String message)
    {
        super(message);
    }
}

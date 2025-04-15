package com.emp.EmployeeManagementSystem.customexception;

public class ResourseNotFoundException extends RuntimeException
{
    public ResourseNotFoundException(String message)
    {
        super(message);
    }
}

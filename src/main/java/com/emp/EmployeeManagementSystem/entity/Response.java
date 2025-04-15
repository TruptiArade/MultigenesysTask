package com.emp.EmployeeManagementSystem.entity;

import lombok.Data;

@Data
public class Response
{
    private String message;
    private Object data;
    private boolean error;

    public Response(String message, Object data, boolean error)
    {
        this.message = message;
        this.data = data;
        this.error = error;
    }

}

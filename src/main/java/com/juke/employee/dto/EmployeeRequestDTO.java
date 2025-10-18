package com.juke.employee.dto;

import lombok.Data;

@Data
public class EmployeeRequestDTO {
    private final String name;
    private final String email;
    private final String position;
    private final Double salary;
}
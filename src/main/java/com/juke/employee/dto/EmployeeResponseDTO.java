package com.juke.employee.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeResponseDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final String position;
    private final Double salary;
    private final LocalDateTime createdAt;
}

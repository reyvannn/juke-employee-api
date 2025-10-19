package com.juke.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EmployeeRequestDTO {
    @NotBlank private final String name;
    @Email @NotBlank private final String email;
    @NotBlank private final String position;
    @Positive private final Double salary;
}
package com.juke.employee.service;

import com.juke.employee.dto.EmployeeRequestDTO;
import com.juke.employee.dto.EmployeeResponseDTO;
import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees();
    EmployeeResponseDTO getEmployeeById(Long id);
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);
    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO);
    void deleteEmployee(Long id);
}
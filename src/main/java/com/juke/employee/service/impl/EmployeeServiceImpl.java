package com.juke.employee.service.impl;

import com.juke.employee.dto.EmployeeRequestDTO;
import com.juke.employee.dto.EmployeeResponseDTO;
import com.juke.employee.exception.DuplicateEmailException;
import com.juke.employee.exception.NotFoundException;
import com.juke.employee.model.Employee;
import com.juke.employee.repository.EmployeeRepository;
import com.juke.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseDTO> employeeResponseDTOS;
        employeeResponseDTOS = employees.stream()
                .map(this::toResponse)
                .toList();
        return employeeResponseDTOS;
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with id %s not found", id)));
        return toResponse(employee);
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        if (employeeRepository.existsByEmail(employeeRequestDTO.getEmail())) {
            log.debug("email {} already exists", employeeRequestDTO.getEmail());
            throw new DuplicateEmailException(String.format("Employee with email %s already exists", employeeRequestDTO.getEmail()));
        }
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setPosition(employeeRequestDTO.getPosition());
        employee.setSalary(employeeRequestDTO.getSalary());
        Employee saved = employeeRepository.save(employee);
        log.info("saved employee {} with ID {}", saved.getName(), saved.getId());
        return toResponse(saved);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Employee with id %s not found", id)));

        // if the email is changed, check if it already exists
        if (!employee.getEmail().equalsIgnoreCase(employeeRequestDTO.getEmail()) && employeeRepository.existsByEmail(employeeRequestDTO.getEmail())) {
            log.debug("email {} already exists", employeeRequestDTO.getEmail());
            throw new DuplicateEmailException(String.format("Employee with email %s already exists", employeeRequestDTO.getEmail()));
        }

        employee.setName(employeeRequestDTO.getName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setPosition(employeeRequestDTO.getPosition());
        employee.setSalary(employeeRequestDTO.getSalary());
        Employee updated = employeeRepository.save(employee);
        log.info("updated employee {} with ID {}", updated.getName(), updated.getId());
        return toResponse(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            log.debug("employee with id {} not found", id);
            throw new NotFoundException(String.format("Employee with id %s not found", id));
        }
        employeeRepository.deleteById(id);
        log.info("deleted employee with id {}", id);
    }


    private EmployeeResponseDTO toResponse(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getCreatedAt()
        );
    }

}

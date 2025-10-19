package com.juke.employee.controller;

import com.juke.employee.dto.EmployeeRequestDTO;
import com.juke.employee.dto.EmployeeResponseDTO;
import com.juke.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> list() {
        List<EmployeeResponseDTO> list = employeeService.getAllEmployees();
        return ResponseEntity.ok(list); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable Long id) {
        EmployeeResponseDTO dto = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(dto); // 200
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "409", description = "Duplicate email")
    })
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> create(@Valid @RequestBody EmployeeRequestDTO req) {
        EmployeeResponseDTO created = employeeService.createEmployee(req);

        // Build Location: /api/employees/{id}
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // current request
                .path("/{id}") // append path variable
                .buildAndExpand(created.getId()) // replace path variable with the actual id
                .toUri();

        return ResponseEntity.created(location).body(created); // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO employeeRequest) {
        EmployeeResponseDTO updated = employeeService.updateEmployee(id, employeeRequest);
        return ResponseEntity.ok(updated); // 200
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build(); // 204
    }
}

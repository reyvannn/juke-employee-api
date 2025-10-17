package com.juke.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String position;
    private Double salary;
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    protected Employee() {
        this.createdAt = LocalDateTime.now();
    }

    public Employee(Long id, String name, String email, String position, Double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.createdAt = LocalDateTime.now();
    }
}

package com.springboot.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {
    private int empId;
    private String name;
    private double salary;
}

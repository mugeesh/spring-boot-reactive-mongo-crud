package com.springboot.reactive.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "employee")
public class Employee {
    @Id
    private int empId;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double salary;
}

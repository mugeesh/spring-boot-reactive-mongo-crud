package com.springboot.reactive.service;

import com.springboot.reactive.dto.EmployeeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Flux<EmployeeDto> getAllEmployee();

    Mono<EmployeeDto> getEmployee(int empId);

    Mono<EmployeeDto> saveEmployee(Mono<EmployeeDto> employeeDtoMono);

    Mono<Void> deleteAllEmployee();

    Mono<Void> deleteEmployeeById(int id);

    Mono<EmployeeDto> updateEmployee(Mono<EmployeeDto> empDtoMono, int id);
}

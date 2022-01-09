package com.springboot.reactive.controller;

import com.springboot.reactive.dto.EmployeeDto;
import com.springboot.reactive.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @GetMapping
    public Flux<EmployeeDto> getAllEmployee(){
        return service.getAllEmployee();
    }

    @GetMapping("/{id}")
    public Mono<EmployeeDto> getEmployee(@PathVariable String id){
        return service.getEmployee(Integer.parseInt(id));
    }

    @GetMapping("/employee-salary-by-range")
    public Flux<EmployeeDto> getEmployeeBySalaryBetweenRange(@RequestParam("min_salary") double min_salary, @RequestParam("max_salary")double max_salary){
        return service.findEmployeeBySalaryBetween(min_salary,max_salary);
    }

    @PostMapping("/addRecord")
    public Mono<EmployeeDto> saveEmployee(@RequestBody Mono<EmployeeDto> employeeDtoMono){
        log.debug("controller method called ...");
        return service.saveEmployee(employeeDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<EmployeeDto> updateEmployee(@RequestBody Mono<EmployeeDto> employeeDtoMono, @PathVariable int id){
        return service.updateEmployee(employeeDtoMono,id);
    }


    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteEmployee(@PathVariable int id){
        return service.deleteEmployeeById(id);
    }

    @DeleteMapping("/deleteAll")
    public Mono<Void> deleteAllEmployee(){
        log.debug("deleting all employee....");
        return service.deleteAllEmployee();
    }

}

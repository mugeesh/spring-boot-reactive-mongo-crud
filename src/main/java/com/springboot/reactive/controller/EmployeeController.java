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

//    @GetMapping("/product-range")
//    public Flux<EmployeeDto> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max")double max){
//        return service.getEmployeeSalaryInRange(min,max);
//    }

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

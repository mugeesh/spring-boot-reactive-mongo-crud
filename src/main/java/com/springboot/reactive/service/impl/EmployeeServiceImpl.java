package com.springboot.reactive.service.impl;


import com.springboot.reactive.dto.EmployeeDto;
import com.springboot.reactive.entity.Employee;
import com.springboot.reactive.repository.EmployeeRepository;
import com.springboot.reactive.service.EmployeeService;
import com.springboot.reactive.utility.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@Slf4j
@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;


    @Override
    public Flux<EmployeeDto> getAllEmployee() {
        // addInsertRandom_50_employee(); //for testing
        return repository.findAll(Sort.by(Sort.Direction.ASC, "empId"))
                .map(AppUtils::entityToDto);
    }

    @Override
    public Mono<EmployeeDto> getEmployee(int empId) {
        return repository.findById(empId).map(AppUtils::entityToDto);
    }


    @Override
    public Mono<EmployeeDto> updateEmployee(Mono<EmployeeDto> empDtoMono, int empId) {

        return  repository.findById(empId)
                .flatMap(e -> empDtoMono.map(AppUtils::dtoToEntity))
                .doOnNext(e -> e.setEmpId(empId))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    @Override
    public Mono<EmployeeDto> saveEmployee(Mono<EmployeeDto> empDtoMono) {
        log.debug("service method called ...");

        // addInsertRandom_50_employee(); //for testing

        return empDtoMono
                .map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);

    }

    @Override
    public Mono<Void> deleteAllEmployee() {
        return repository.deleteAll();
    }

    @Override
    public Mono<Void> deleteEmployeeById(int id) {
        return repository.deleteById(id);
    }


    void addInsertRandom_50_employee() {
        Flux<Employee> list
                = Flux.range(1, 50)
                .doOnNext(n -> System.out.println("saving employee id :: " + n))
                .map(this::createEmployee)
                .flatMap(repository::insert);
        //saving
        list.subscribe();
    }

    Employee createEmployee(int x) {
        int rand = new Random().nextInt(15) + x;
        double salary = rand * 9000;
        return new Employee(x, "employee" + x, salary);
    }

}

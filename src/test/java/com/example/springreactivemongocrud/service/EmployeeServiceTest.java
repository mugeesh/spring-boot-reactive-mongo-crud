package com.example.springreactivemongocrud.service;

import com.springboot.reactive.dto.EmployeeDto;
import com.springboot.reactive.entity.Employee;
import com.springboot.reactive.repository.EmployeeRepository;
import com.springboot.reactive.service.impl.EmployeeServiceImpl;
import com.springboot.reactive.utility.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
@ExtendWith(SpringExtension.class)
@Slf4j
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl service;

    @Mock
    private EmployeeRepository repository;

    Employee employee;
    Mono<EmployeeDto> empDtoMono;

    @BeforeEach
    public void init() {
        log.info("startup test case");
        employee = Employee.builder()
                .empId(1)
                .name("employee1")
                .salary(1000)
                .build();

        empDtoMono=Mono.just(AppUtils.entityToDto(employee));
    }
    @Test
    public void shouldAddEmployee() {

        when(repository.insert(employee)).thenReturn(Mono.just(employee));

        Mono<EmployeeDto> empdtoMono = service.saveEmployee(empDtoMono);

        StepVerifier
                .create(empdtoMono)
                .consumeNextWith(newUser -> {
                   assert  newUser.getEmpId()==employee.getEmpId();
                   assert  newUser.getName().equals(employee.getName());
                   assert  newUser.getSalary()==employee.getSalary();
                })
                .verifyComplete();
    }

    @Test
    public void shouldGetByEmployeeId() {

        when(repository.findById(1)).thenReturn(Mono.just(employee));

        Mono<EmployeeDto> empdtoMono = service.getEmployee(1);

        StepVerifier
                .create(empdtoMono)
                .consumeNextWith(newUser -> {
                    assert  newUser.getEmpId()==employee.getEmpId();
                    assert  newUser.getName().equals(employee.getName());
                    assert  newUser.getSalary()==employee.getSalary();
                })
                .verifyComplete();
    }
    @Test
    public void shouldUpdateEmployeeById() {

        when(repository.findById(1)).thenReturn(Mono.just(employee));

        when(repository.save(employee)).thenReturn(Mono.just(employee));


        Mono<EmployeeDto> empdtoMono = service.updateEmployee(empDtoMono, 1);

        StepVerifier
                .create(empdtoMono)
                .consumeNextWith(newUser -> {
                    assert  newUser.getEmpId()==employee.getEmpId();
                    assert  newUser.getName().equals(employee.getName());
                    assert  newUser.getSalary()==employee.getSalary();
                })
                .verifyComplete();

        }

}

package com.springboot.reactive.repository;

import com.springboot.reactive.entity.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {

    @Query("{ 'salary' : { $gt: ?0, $lt: ?1 } }")
    Flux<Employee> findEmployeeBySalaryBetween(double min_salary, double max_salary);
}

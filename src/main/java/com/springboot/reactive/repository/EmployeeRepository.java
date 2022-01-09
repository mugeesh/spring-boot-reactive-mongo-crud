package com.springboot.reactive.repository;

import com.springboot.reactive.entity.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {

}

package com.example.employee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.employee.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}

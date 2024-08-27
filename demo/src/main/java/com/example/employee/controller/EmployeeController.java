package com.example.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	
	@GetMapping("/hello")
	public String Hello() {
		return "Good Morning...";
	}
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping
	public List<Employee> getEmployees(){
		return employeeService.getAllEmployee();
	}
	
	@GetMapping("/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable String id) {
		return employeeService.getEmployeeById(id);
	}
	
	@PutMapping("/update-employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee newEmployee){
		return employeeService.updateEmployee(id, newEmployee);
	}
	
	@DeleteMapping("/delete-employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
		return employeeService.deleteEmployee(id);
	}
}




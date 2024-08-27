package com.example.employee.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	
	public Employee addEmployee(Employee employee) {
		employee.setId(UUID .randomUUID().toString().split("-")[0]);
		return employeeRepo.save(employee);
	}
	
	public List<Employee> getAllEmployee(){
		return employeeRepo.findAll();
	}
	
	public Optional<Employee> getEmployeeById(String id) {
		 return employeeRepo.findById(id);
	}
	
	public ResponseEntity<Employee> updateEmployee(String id, Employee newEmployee) {
		Optional<Employee> existedEmployee = employeeRepo.findById(id);
		
		if(existedEmployee.isPresent()) {
			Employee employee = existedEmployee.get();
			
			employee.setName(newEmployee.getName());
			employee.setRole(newEmployee.getRole());
			employee.setCompany(newEmployee.getCompany());
			employee.setLocation(newEmployee.getLocation());
			
			return ResponseEntity.ok(employeeRepo.save(employee));
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<?> deleteEmployee(String id) {
		 try {
	            Optional<Employee> employee = employeeRepo.findById(id);
	            if (employee.isPresent()) {
	                employeeRepo.deleteById(id);
	                return ResponseEntity.ok("Employee Deleted");
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();

	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Failed to delete employee with ID: " + id);
	        }	
	}
}

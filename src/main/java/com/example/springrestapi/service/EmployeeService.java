package com.example.springrestapi.service;

import java.util.List;

import com.example.springrestapi.model.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
	
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(long id);
	
	Employee updateEmployeeById(Employee employee, long id);
	
	void deleteEmployee(long id);
	
	List<Employee> getEmployeeByFirstName(String fname);
	
	List<Employee> getEmployeeByLastName(String lname);
	
	List<Employee> getEmployeeByEmail(String email);
	
	List<Employee> getEmployeeByFirstAndLastName(String fname, String lname);
	
	
}

package com.example.springrestapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springrestapi.exception.ResourceNotFoundException;
import com.example.springrestapi.model.Employee;
import com.example.springrestapi.repository.EmployeeRepository;
import com.example.springrestapi.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	

	private EmployeeRepository employeeRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new ResourceNotFoundException("Emplooyee","Id",id);
		}
		
	}

	@Override
	public Employee updateEmployeeById(Employee employee, long id) {
		//checking if employee with given id exists in db
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee","Id",id));
		
		existingEmployee.setFirstName(employee.getFirstName());	
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		//save employee to db
		employeeRepository.save(existingEmployee);
		return existingEmployee;
				
	}

	@Override
	public void deleteEmployee(long id) {
		
		//check whether a employee exist in db
		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
		
		employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> getEmployeeByFirstName(String fname) {
		return employeeRepository.getByFnameQuery(fname);
	}

	@Override
	public List<Employee> getEmployeeByLastName(String lname) {
		return employeeRepository.getByLnameQuery(lname);
	}

	@Override
	public List<Employee> getEmployeeByEmail(String email) {
		return employeeRepository.getByEmailQuery(email);
	}

	@Override
	public List<Employee> getEmployeeByFirstAndLastName(String fname, String lname) {
		return employeeRepository.getByFnameAndLnameQuery(fname,lname);
	}

}

package com.example.springrestapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestapi.model.Employee;
import com.example.springrestapi.service.EmployeeService;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public List<Employee> getEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Employee>  getEmployee(@PathVariable("id") String id){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(Long.parseLong(id)), HttpStatus.OK);
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployeeById(employee, Long.parseLong(id)), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id){
		employeeService.deleteEmployee(Long.parseLong(id));
		return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
	}
	
	//Handling Query Parameters
	
	@GetMapping("/queryFirst")
	public ResponseEntity<List<Employee>> queryEmployeeFname(@RequestParam(name="Fname") String fname){
		return new ResponseEntity<>(employeeService.getEmployeeByFirstName(fname), HttpStatus.OK);
		
	}
	
	@GetMapping("/queryLast")
	public ResponseEntity<List<Employee>> queryEmployeeLname(@RequestParam(name="Lname") String lname){
		return new ResponseEntity<>(employeeService.getEmployeeByLastName(lname), HttpStatus.OK);
		
	}
	@GetMapping("/queryEmail")
	public ResponseEntity<List<Employee>> queryEmployeeEmail(@RequestParam(name="Email") String email){
		return new ResponseEntity<>(employeeService.getEmployeeByEmail(email), HttpStatus.OK);
		
	}
	@GetMapping("/queryFirstLast")
	public ResponseEntity<List<Employee>> queryEmployeeFnameLname(@RequestParam(name="Fname") String fname, @RequestParam(name="Lname") String lname){
		return new ResponseEntity<>(employeeService.getEmployeeByFirstAndLastName(fname,lname), HttpStatus.OK);
		
	}
	
}

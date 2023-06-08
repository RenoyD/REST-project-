package com.example.springrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springrestapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	

	@Query("SELECT f FROM employeeEntity f WHERE f.firstName=?1")
	public List<Employee> getByFnameQuery(String fname);
	
	@Query("SELECT l FROM employeeEntity l WHERE l.lastName=?1")
	public List<Employee> getByLnameQuery(String lname);
	
	@Query("SELECT e FROM employeeEntity e WHERE e.email=?1")
	public List<Employee> getByEmailQuery(String email);
	
	@Query("SELECT fn_ln FROM employeeEntity fn_ln WHERE fn_ln.firstName=?1 AND fn_ln.lastName=?2")
	public List<Employee> getByFnameAndLnameQuery(String fname, String lname);
}

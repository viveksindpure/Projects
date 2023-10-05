package com.velocity.service;

import java.util.List;

import com.velocity.entity.Employee;

public interface EmployeeService {
	
	public Employee saveEmployee(Employee employee);
	
	List<Employee> getAllEmployee();

	
 
	Employee getEmployeeById(int id);

	int updateEmployee(int id, Employee employee);

	String deleteEmployeeById(int id);
	
	List<Employee> getAllEmployeeByPage(int page, int size);
	
	List<Employee> getAllEmployeeBySorting();

	

	

}

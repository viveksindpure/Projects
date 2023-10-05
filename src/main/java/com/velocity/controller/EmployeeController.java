package com.velocity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.velocity.entity.Employee;
import com.velocity.exception.NoSuchElementFoundException;
import com.velocity.repository.EmployeeRepository;
import com.velocity.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping(path="/save" ,consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Employee  addEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
		
	}
	 
	@GetMapping("/allEmp")
	public List<Employee> getAll() {
		//return employeeService.getAllEmployee();
		return employeeService.getAllEmployeeBySorting();
		
	}
	
	@GetMapping("/allEmp/{page}")
	public ResponseEntity<List<Employee>> getEmployeeByPage(@PathVariable int page) {
		List<Employee> byPage = employeeService.getAllEmployeeByPage(page, 4);
		return new ResponseEntity<List<Employee>>(byPage, HttpStatus.OK) ;
		
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable int id) {
		Employee employee = employeeService.getEmployeeById(id);
		if(employee != null)
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		else 
			throw new NoSuchElementFoundException("Employee with this id not found");
	}
	@GetMapping(path="/{city}/{name}")
	public ResponseEntity<Employee> test(@PathVariable String city,@PathVariable String name) {
		Employee findByCityAndName = employeeRepository.getdata(city, name);
		return new ResponseEntity<Employee>(findByCityAndName, HttpStatus.BAD_GATEWAY);
		
	}
	 
	@GetMapping("/request")
	public Employee getById(@RequestParam ("empcode")int id) { 
		Employee employee = employeeService.getEmployeeById(id);
		return employee;
	}
	
	@PutMapping("/update")
	public String updateEmp(@RequestBody Employee employee) {
		int id = employeeService.updateEmployee(employee.getId(),employee);
		if(id ==0) {
			return "employee not found";
		}
		return String.format("Employee is updated for the id ::{}", id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmpl(@PathVariable int id) {
		String response = employeeService.deleteEmployeeById(id);
		return response; 
		
	}
}

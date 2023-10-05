package com.velocity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.velocity.entity.Employee;
import com.velocity.exception.NoSuchElementFoundException;
import com.velocity.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl  implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);
		return savedEmployee;
	}
 
	@Override
	public List<Employee> getAllEmployee() {
		Iterable<Employee> iterable = employeeRepository.findAll();
		return (List<Employee>) iterable;
		
	}

	@Override
	public Employee getEmployeeById(int id) {
	//	return employeeRepository.findById(id).orElseThrow(()-> new NoSuchElementFoundException("Element with given id not found"));
//		if (response.isPresent()) {
//			return response.get();
//		} 
//		return response.isPresent() ? response.get() : null;
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		return null;
		
	}

	@Override
	public int updateEmployee(int id, Employee employee) {
		Optional<Employee> response = employeeRepository.findById(id);
	if(response.isPresent())
	{
		Employee result = employeeRepository.save(employee);
		return result.getId();
	}
		return 0 ;
 	} 

	@Override
	public String deleteEmployeeById(int id) {
		employeeRepository.deleteById(id);
		return "Employee deleted";
	}

	@Override
	public List<Employee> getAllEmployeeByPage(int page, int size) {
		PageRequest request = PageRequest.of(1, 4);
		Page<Employee> pageResponse = employeeRepository.findAll(request);
		List<Employee> listEmployees = pageResponse.getContent();
		return listEmployees;
	}

	@Override
	public List<Employee> getAllEmployeeBySorting() {
		List<Employee> findAll = employeeRepository.findAll(Sort.by("city").ascending());
		
		return findAll;
	}

	

	

	
}
   
package com.velocity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.velocity.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public List<Employee> findByCity(String city);
	 
	public Employee findByCityAndName(String city, String name);
	
	@Query(value="Select * from employee where Emp_City = :city and Emp_Name = :name " , nativeQuery =  true)
	public Employee getdata(@Param(value = "city") String city, @Param(value = "name") String name);

}

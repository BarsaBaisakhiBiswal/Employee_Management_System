package com.EmployeeManagementSystem.EmployeeManagementSystem.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Employee;

public interface IEmployee {

	
	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	List<Employee> getAllEmployees();
	void deleteEmployeeById(long id);
}

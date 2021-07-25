package com.EmployeeManagementSystem.EmployeeManagementSystem.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Department;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Employee;

public interface IDepartment {

	
	Page<Department> findPaginatedDepartment(int pageNo, int pageSize, String sortField, String sortDirection);
	void saveDepartment(Department department);
	public Department getDepartmentById(long id);
	List<Department> getAllDepartment();
	public void deleteDepartmentById(long id);

}

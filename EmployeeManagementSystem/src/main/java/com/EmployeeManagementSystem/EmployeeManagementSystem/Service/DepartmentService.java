package com.EmployeeManagementSystem.EmployeeManagementSystem.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Controller.EMS_Controller;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Dao.DepartmentRepository;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Dao.EmployeeRepository;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Department;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Employee;
@Service
public class DepartmentService implements IDepartment{

//	EMS_Controller ems_controller = new EMS_Controller();
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private static final Logger logger = LogManager.getLogger(DepartmentService.class);
	
	
	
	@Override
	public Department getDepartmentById(long id) {
		Optional<Department> optional = departmentRepository.findById(id);
		Department department = null;
		if (optional.isPresent()) {
			department = optional.get();
		} else {
			logger.error(" Department not found for id :: " + id);
			throw new RuntimeException(" Department not found for id :: " + id);
		}
		return department;
	}
	
	@Override
	public Page<Department> findPaginatedDepartment(int pageNo, int pageSize, String sortField, String sortDirection) {
		// TODO Auto-generated method stub
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return departmentRepository.findAll(pageable); 
	}
	
	@Override
	public void saveDepartment(Department department) {
		departmentRepository.save(department);
		logger.info("Department Added");
	}

	@Override
	public List<Department> getAllDepartment() {
		return (List<Department>) departmentRepository.findAll();
	}
	@Override
	public void deleteDepartmentById(long id) {
		try {
			this.departmentRepository.deleteById(id);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
	}

}

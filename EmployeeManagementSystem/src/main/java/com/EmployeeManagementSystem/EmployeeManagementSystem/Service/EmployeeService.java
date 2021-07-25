package com.EmployeeManagementSystem.EmployeeManagementSystem.Service;

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

import com.EmployeeManagementSystem.EmployeeManagementSystem.EmployeeManagementSystemApplication;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Controller.EMS_Controller;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Dao.DepartmentRepository;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Dao.EmployeeRepository;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Department;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Employee;
@Service
public class EmployeeService implements IEmployee {

//	EMS_Controller ems_controller = new EMS_Controller();
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	private static final Logger logger = LogManager.getLogger(EmployeeService.class);
	
	
	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			 logger.error("Employee Not Found");
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}
	
	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		// TODO Auto-generated method stub
		
		try {
			Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();
			
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
			return employeeRepository.findAll(pageable); 
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
		logger.info("Employee Added");
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	@Override
	public void deleteEmployeeById(long id) {
		try {
			this.employeeRepository.deleteById(id);
			logger.info("Employee deleted");
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
	}
}

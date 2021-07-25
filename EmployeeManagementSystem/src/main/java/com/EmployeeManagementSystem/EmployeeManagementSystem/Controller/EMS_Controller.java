package com.EmployeeManagementSystem.EmployeeManagementSystem.Controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Dao.DepartmentRepository;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Dao.EmployeeRepository;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Department;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Employee;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Service.IEmployee;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Service.EmployeeService;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Service.IDepartment;

@Controller
public class EMS_Controller {
	@Autowired
	private IEmployee employee;
	@Autowired
	private IDepartment department;
	private ApplicationContext context;
	private DepartmentRepository departmentRepository;
	private EmployeeRepository employeeRepository;
	
	public EMS_Controller(ApplicationContext context) {
		this.context = context;
		departmentRepository = context.getBean(DepartmentRepository.class);
		employeeRepository = context.getBean(EmployeeRepository.class);
	}

	public EMS_Controller() {
		super();
	}

	public ApplicationContext getContext() {
		return context;
	}

	public DepartmentRepository getDepartmentRepository() {
		return departmentRepository;
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}
	
	
	
	/* Barsa
	 * 
	 */
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);		
	}
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Employee> page = employee.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	
	
	
	@GetMapping("/showFormForUpdate/{empId}")
	public String showFormForUpdate(@PathVariable ( value = "empId") int id, Model model) {
		
		// get employee from the service
		Employee employee1 = employee.getEmployeeById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee1);
		return "update_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee1) {
		// save employee to database
		employee.saveEmployee(employee1);
		return "redirect:/";
	}
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.employee.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	
	
	
	
	/* Barsa
	 * 
	 */
	@GetMapping("/getDepartment")
	public String viewHomePageDepartment(Model model) {
		return findPaginatedDepartment(1, "deptName", "asc", model);		
	}
	@GetMapping("/pageDepartment/{pageNo}")
	public String findPaginatedDepartment(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Department> page = department.findPaginatedDepartment(pageNo, pageSize, sortField, sortDir);
		List<Department> listDepartments = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listDepartments", listDepartments);
		return "indexDepartment";
	}
	@GetMapping("/showNewDepartmentForm")
	public String showNewDepartmentForm(Model model) {
		// create model attribute to bind form data
		Department department = new Department();
		model.addAttribute("department", department);
		return "new_department";
	}
	
	
	
	
	@GetMapping("/showFormForUpdateDepartment/{deptId}")
	public String showFormForUpdateDepartment(@PathVariable ( value = "deptId") long id, Model model) {
		
		// get employee from the service
		Department department1 = department.getDepartmentById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("department", department1);
		return "update_department";
	}
	
	@PostMapping("/saveDepartment")
	public String saveDepartment(@ModelAttribute("department") Department department) {
		// save employee to database
		this.department.saveDepartment(department);
		return "redirect:/getDepartment";
	}
	@GetMapping("/deleteDepartment/{id}")
	public String deleteDepartment(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.department.deleteDepartmentById(id);
		return "redirect:/getDepartment";
	}
	
	
	
	
}

package com.EmployeeManagementSystem.EmployeeManagementSystem.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}

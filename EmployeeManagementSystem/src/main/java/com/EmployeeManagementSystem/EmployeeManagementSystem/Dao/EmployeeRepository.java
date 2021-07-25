package com.EmployeeManagementSystem.EmployeeManagementSystem.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}

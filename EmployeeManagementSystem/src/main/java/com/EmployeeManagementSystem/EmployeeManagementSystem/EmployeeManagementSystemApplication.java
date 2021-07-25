package com.EmployeeManagementSystem.EmployeeManagementSystem;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Controller.EMS_Controller;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Dao.DepartmentRepository;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Dao.EmployeeRepository;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Department;
import com.EmployeeManagementSystem.EmployeeManagementSystem.Entities.Employee;

@SpringBootApplication
public class EmployeeManagementSystemApplication implements ApplicationRunner{

	private static final Logger logger = LogManager.getLogger(EmployeeManagementSystemApplication.class);
	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
//		logger.trace("A TRACE Message");
//        logger.debug("A DEBUG Message");
//        logger.info("An INFO Message");
//        logger.warn("A WARN Message");
//        logger.error("An ERROR Message");
	}

}

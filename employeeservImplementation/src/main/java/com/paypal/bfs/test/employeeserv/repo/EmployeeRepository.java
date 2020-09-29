package com.paypal.bfs.test.employeeserv.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.bfs.test.employeeserv.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}

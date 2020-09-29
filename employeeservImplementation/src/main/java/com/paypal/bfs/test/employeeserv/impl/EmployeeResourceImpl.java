package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;

import ma.glasnost.orika.MapperFacade;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    EmployeeRepository empRepo;

    @Autowired
    MapperFacade mapper;

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        Optional<com.paypal.bfs.test.employeeserv.domain.Employee> emp = empRepo.findById(Integer.valueOf(id));

        if (emp.isPresent()) {
            return new ResponseEntity<>(mapper.map(emp.get(), Employee.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> createEmployee(Employee employee) {

        if (empRepo.findById(Integer.valueOf(employee.getId())).isPresent()) {
            return new ResponseEntity<>("Employee Already Exist!", HttpStatus.BAD_REQUEST);
        }

        empRepo.save(mapper.map(employee, com.paypal.bfs.test.employeeserv.domain.Employee.class));
        return new ResponseEntity<>("Employee Added!", HttpStatus.CREATED);
    }
}

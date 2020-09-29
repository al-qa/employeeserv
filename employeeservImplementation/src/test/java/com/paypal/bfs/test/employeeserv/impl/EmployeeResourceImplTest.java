package com.paypal.bfs.test.employeeserv.impl;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;

import junit.framework.Assert;
import ma.glasnost.orika.MapperFacade;

@RunWith(SpringRunner.class)
public class EmployeeResourceImplTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
 
        @Bean
        public EmployeeResourceImpl employeeService() {
            return new EmployeeResourceImpl();
        }
    }

    @Autowired
    EmployeeResourceImpl employeeService;

    @MockBean
    private EmployeeRepository empRepo;

    @MockBean
    MapperFacade mapper;

    @Test
    public void testCreateEmployeeSuccess() {

        Employee emp = new Employee();
        emp.setId(1);
        emp.setFirstName("BFS");
        emp.setLastName("Developer");
        emp.setDateOfBirth("9/28/2020");

        Address add = new Address();
        add.setLine1("123 main st");
        add.setCity("silver spring");
        add.setState("MD");
        add.setCountry("USA");
        add.setZip("20902");
        emp.setAddress(add);

        Assert.assertEquals(HttpStatus.CREATED, employeeService.createEmployee(emp).getStatusCode());
    }

    @Test
    public void testCreateEmployeeFail() {

        Employee emp = new Employee();
        emp.setId(1);
        emp.setFirstName("BFS");
        emp.setLastName("Developer");
        emp.setDateOfBirth("9/28/2020");

        Address add = new Address();
        add.setLine1("123 main st");
        add.setCity("silver spring");
        add.setState("MD");
        add.setCountry("USA");
        add.setZip("20902");
        emp.setAddress(add);

        Mockito.when(empRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(new com.paypal.bfs.test.employeeserv.domain.Employee()));
        Assert.assertEquals(HttpStatus.BAD_REQUEST, employeeService.createEmployee(emp).getStatusCode());
    }

    @Test
    public void testGetByIdSuccess() {

        Mockito.when(empRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(new com.paypal.bfs.test.employeeserv.domain.Employee()));
        Assert.assertEquals(HttpStatus.OK, employeeService.employeeGetById("1").getStatusCode());
    }

    @Test
    public void testGetByIdFail() {

        Mockito.when(empRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatus.NOT_FOUND, employeeService.employeeGetById("1").getStatusCode());
    }
}

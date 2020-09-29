package com.paypal.bfs.test.employeeserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.paypal.bfs.test.employeeserv.mapper.EmployeeMapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@SpringBootApplication
public class EmployeeservApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeservApplication.class, args);
    }

    @Bean
    public MapperFacade domainToModelMapper(){
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        EmployeeMapper.register(factory);
        return factory.getMapperFacade();
    }
}
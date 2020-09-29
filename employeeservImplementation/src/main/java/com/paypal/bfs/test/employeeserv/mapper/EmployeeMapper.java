package com.paypal.bfs.test.employeeserv.mapper;

import com.paypal.bfs.test.employeeserv.domain.Address;
import com.paypal.bfs.test.employeeserv.domain.Employee;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

public class EmployeeMapper extends ConfigurableMapper {

    private EmployeeMapper() {}

    public static void register(MapperFactory factory) {

        factory.classMap(Employee.class, com.paypal.bfs.test.employeeserv.api.model.Employee.class).field("empId", "id").byDefault().register();
        factory.classMap(Address.class, com.paypal.bfs.test.employeeserv.api.model.Address.class).field("addId", "id").byDefault().register();
    }
}

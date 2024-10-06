package com.employee.management.system.www.controller;

import com.employee.management.system.www.model.Employee;
import com.employee.management.system.www.service.EmployeeGraphQlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphQlController {

    @Autowired
    private EmployeeGraphQlService employeeGraphQlService;

    @QueryMapping
    public List<Employee> getAllEmployeeList() {
        return employeeGraphQlService.getAllEmployeeData();
    }

    @QueryMapping
    public List<Employee> getFilteredEmployeeList() {
        return employeeGraphQlService.getFilteredEmployeesEmail();
    }

    @QueryMapping
    public Optional<Employee> getEmployeeId(@Argument @Valid int id) {
        return employeeGraphQlService.getEmployeeById(id);
    }
}

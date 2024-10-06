package com.employee.management.system.www.service;

import com.employee.management.system.www.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeGraphQlService {

    List<Employee> getAllEmployeeData();
    List<Employee> getFilteredEmployeesEmail();
    Optional<Employee> getEmployeeById(int id);
}

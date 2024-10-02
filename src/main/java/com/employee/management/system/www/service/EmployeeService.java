package com.employee.management.system.www.service;

import com.employee.management.system.www.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface EmployeeService {
    ResponseEntity getAllEmployeeData();
    ResponseEntity createEmployees(List<Employee> employees);
    ResponseEntity getFilteredEmployeesEmail();
    ResponseEntity getEmployeeById(int id);
    ResponseEntity deleteById(int id);
    ResponseEntity updateEmployee(int id, Employee employee);

}

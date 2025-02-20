package com.employee.management.system.www.service;

import com.employee.management.system.www.model.Employee;
import com.employee.management.system.www.model.EmployeeCredentials;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface EmployeeService {
    ResponseEntity getAllEmployeeData();
    ResponseEntity createEmployees(List<Employee> employees);
    ResponseEntity getFilteredEmployeesEmail();
    ResponseEntity getEmployeeById(int id);
    ResponseEntity deleteById(int id);
    ResponseEntity updateEmployee(int id, Employee employee);
    ResponseEntity<EmployeeCredentials> createCredentials(List<EmployeeCredentials> credentials);
    List<EmployeeCredentials> getCredentials();
}

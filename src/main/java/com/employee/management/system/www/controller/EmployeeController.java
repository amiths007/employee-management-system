package com.employee.management.system.www.controller;

import com.employee.management.system.www.model.Employee;
import com.employee.management.system.www.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/get/all")
    public ResponseEntity<List<Employee>> getAllEmployeeList() {
        return employeeService.getAllEmployeeData();
    }

    @GetMapping("/get/filtered/employees")
    public ResponseEntity<List<Employee>> getFilteredEmployeeList() {
        return employeeService.getFilteredEmployeesEmail();
    }

    @PostMapping("/post/employees")
    public ResponseEntity<List<Employee>> create(@RequestBody @Valid List<Employee> employee) {
        return employeeService.createEmployees(employee);
    }

    @GetMapping("/get/employee/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeId(@PathVariable @Valid int id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable @Valid int id) {
        return employeeService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody @Valid Employee employee) {
        return employeeService.updateEmployee(id, employee);

    }
}

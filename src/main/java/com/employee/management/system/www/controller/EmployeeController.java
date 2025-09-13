package com.employee.management.system.www.controller;

import com.employee.management.system.www.model.Employee;
import com.employee.management.system.www.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/get/all")
    public ResponseEntity getAllEmployeeList() {
        return ResponseEntity.ok(employeeService.getAllEmployeeData());
    }

    @GetMapping("/get/filtered/employees")
    public ResponseEntity getFilteredEmployeeList() {
        return ResponseEntity.ok(employeeService.getFilteredEmployeesEmail());
    }

    @PostMapping("/post/employees")
    public ResponseEntity create(@RequestBody @Valid List<Employee> employee) {
        return ResponseEntity.ok(employeeService.createEmployees(employee));
    }

    @GetMapping("/get/employee/{id}")
    public ResponseEntity getEmployeeId(@PathVariable @Valid int id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable @Valid int id) {
        return ResponseEntity.ok(employeeService.deleteById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@PathVariable int id, @RequestBody @Valid Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));

    }
}

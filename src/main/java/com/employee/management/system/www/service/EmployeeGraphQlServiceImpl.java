package com.employee.management.system.www.service;

import com.employee.management.system.www.mapper.ReqresResponseMapper;
import com.employee.management.system.www.model.Employee;
import com.employee.management.system.www.model.UserDataResponse;
import com.employee.management.system.www.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeGraphQlServiceImpl implements EmployeeGraphQlService{

    private final Logger logger = LoggerFactory.getLogger(EmployeeGraphQlServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserDataServiceImpl userDataService;

    @Autowired
    private ReqresResponseMapper reqresResponseMapper;

    public List<Employee> getAllEmployeeData() {
        logger.debug("Fetching employee records...");
        return employeeRepository.findAll();
    }

    public List<Employee> getFilteredEmployeesEmail() {
        logger.debug("Fetching filtered employee records...");
        UserDataResponse response = userDataService.getUserData();
        List<Employee> employees = employeeRepository.findAll();

        if (!CollectionUtils.isEmpty(employees)) {
            List<Employee> list = employees.stream().filter(filtered -> response.getUserDataList().stream().anyMatch(email -> email.getEmail().equals(filtered.getEmail()))).collect(Collectors.toList());
            List<Employee> filteredList = reqresResponseMapper.userDataResponseMapper(response, list);
            logger.info("Filtered employee records - {} ", filteredList);
            return ResponseEntity.ok(filteredList).getBody();
        }

        return employees;
    }

    public Optional<Employee> getEmployeeById(int id) {
        logger.debug("Fetching employee records by id - {}", id);
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!Objects.isNull(employee) && employeeRepository.existsById(id)) {
            logger.info("Employee records by id - {} is {} ", id, employee);
        }
        return employee;
    }
}
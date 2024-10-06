package com.employee.management.system.www.service;


import com.employee.management.system.www.constants.ConfigConstants;
import com.employee.management.system.www.mapper.ReqresResponseMapper;
import com.employee.management.system.www.model.Employee;
import com.employee.management.system.www.model.FinanceDetails;
import com.employee.management.system.www.model.UserDataResponse;
import com.employee.management.system.www.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CircuitBreaker(name = ConfigConstants.CIRCUIT_BREAKER_NAME)
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserDataServiceImpl userDataService;

    @Autowired
    private ReqresResponseMapper reqresResponseMapper;

    public ResponseEntity getAllEmployeeData() {
        logger.debug("Fetching employee records...");
        List<Employee> employees = employeeRepository.findAll();
        if (!CollectionUtils.isEmpty(employees)) {
            logger.info("Employee records - {} ", employees);
            return ResponseEntity.of(Optional.of(employees));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No Records Founds in Database...Please try again later");
        }
    }

    public ResponseEntity getFilteredEmployeesEmail() {
        logger.debug("Fetching filtered employee records...");
        UserDataResponse response = userDataService.getUserData();
        List<Employee> employees = employeeRepository.findAll();

        if (!CollectionUtils.isEmpty(employees)) {
            List<Employee> list = employees.stream().filter(filtered -> response.getUserDataList().stream().anyMatch(email -> email.getEmail().equals(filtered.getEmail()))).collect(Collectors.toList());
            List<Employee> filteredList = reqresResponseMapper.userDataResponseMapper(response, list);
            logger.info("Filtered employee records - {} ", filteredList);
            return ResponseEntity.ok(filteredList);
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity createEmployees(List<Employee> employees) {
        logger.debug("Saving employee records...");
        if (!CollectionUtils.isEmpty(employees)) {
            List<Employee> employee = employeeRepository.saveAll(employees);
            logger.info("Created employee records - {} ", employee);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Employee record created successfully..");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while saving Employee Data!!. Kindly provide data ");
        }
    }

    public ResponseEntity getEmployeeById(int id) {
        logger.debug("Fetching employee records by id - {}", id);
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!Objects.isNull(employee) && employeeRepository.existsById(id)) {
            logger.info("Employee records by id - {} is {} ", id, employee);
            return ResponseEntity.of(Optional.of(employee));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee with id: " + id + " don't exist in the database...");
        }
    }

    public ResponseEntity deleteById(int id) {
        logger.debug("Deleting employee records by id - {}", id);
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            logger.info("Deleted records by id - {} ", id);
            return ResponseEntity.status(HttpStatus.OK).body("Employee record deleted successfully...");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Provided id: " + id + " is invalid or doesn't exist in the database...");
        }
    }

    public ResponseEntity updateEmployee(int id, Employee employee) {
        logger.debug("Updating employee records...");
        Employee emp = employeeRepository.findById(id).get();

        if (!Objects.isNull(emp)) {
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setAge(employee.getAge());
            emp.setCompanyName(employee.getCompanyName());

            FinanceDetails financeDetails = new FinanceDetails();
            financeDetails.setGrade(employee.getFinanceDetails().getGrade());
            financeDetails.setRole(employee.getFinanceDetails().getRole());
            financeDetails.setSalary(employee.getFinanceDetails().getSalary());

            emp.setFinanceDetails(financeDetails);
            employeeRepository.save(emp);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error while updating Employee data of id: " + id + "Try again!!");
        }
        logger.info("Updated employee records - {} ", emp);
        return ResponseEntity.status(HttpStatus.OK).body("Employee record deleted successfully...");
    }
}

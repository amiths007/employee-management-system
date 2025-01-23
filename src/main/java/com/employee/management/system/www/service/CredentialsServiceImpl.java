package com.employee.management.system.www.service;

import com.employee.management.system.www.model.Employee;
import com.employee.management.system.www.model.EmployeeCredentials;
import com.employee.management.system.www.repository.CredentialsRepository;
import com.employee.management.system.www.util.EmployeeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CredentialsServiceImpl implements CredentialsService{

    private final Logger logger = LoggerFactory.getLogger(CredentialsServiceImpl.class);

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private EmployeeUtils employeeUtils;

    public ResponseEntity createCredentials(List<EmployeeCredentials> credentials) {
        logger.debug("Saving employee records...");
        if (!CollectionUtils.isEmpty(credentials)) {
            List<EmployeeCredentials> credentialsList = null;
            credentialsList = employeeUtils.encodeCredentials(credentials);
            credentialsList = credentialsRepository.saveAll(credentialsList);
            logger.info("Created employee credentials - {} ", credentialsList);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Employee credentials created successfully..");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while saving Employee Credentials!!. Kindly provide data ");
        }
    }
}

package com.employee.management.system.www.service;

import com.employee.management.system.www.model.EmployeeCredentials;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CredentialsService {

    public ResponseEntity createCredentials(List<EmployeeCredentials> credentials);
}

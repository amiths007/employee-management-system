package com.employee.management.system.www.controller;

import com.employee.management.system.www.model.EmployeeCredentials;
import com.employee.management.system.www.service.CredentialsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    @PostMapping("/post/credentials")
    public ResponseEntity create(@RequestBody @Valid List<EmployeeCredentials> credentials) {
        return credentialsService.createCredentials(credentials);
    }

}

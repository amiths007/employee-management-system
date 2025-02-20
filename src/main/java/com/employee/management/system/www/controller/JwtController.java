package com.employee.management.system.www.controller;

import com.employee.management.system.www.model.EmployeeCredentials;
import com.employee.management.system.www.service.MyUserDetailService;
import com.employee.management.system.www.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/jwtToken")
    public ResponseEntity getBearerToken(@RequestBody EmployeeCredentials credentials) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUserName(), credentials.getPassword()));
            if (authentication.isAuthenticated())
                return ResponseEntity.ok(jwtUtil.generateJwtToken(credentials.getUserName()));

        } catch (Exception ex) {
            return ResponseEntity.status(404)
                    .body("Unable to authorize the request!!, Please provide valid credentials or does not exist in our database");
        }
        return null;
    }
}
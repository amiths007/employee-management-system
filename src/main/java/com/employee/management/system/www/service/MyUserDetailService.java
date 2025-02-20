package com.employee.management.system.www.service;

import com.employee.management.system.www.model.EmployeeCredentials;
import com.employee.management.system.www.model.MyUserCredentials;
import com.employee.management.system.www.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeCredentials user = credentialsRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not found");
        }

        return new MyUserCredentials(user);
    }
}

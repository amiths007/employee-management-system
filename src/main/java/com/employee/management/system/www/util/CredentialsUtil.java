package com.employee.management.system.www.util;

import com.employee.management.system.www.model.EmployeeCredentials;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Component
public class CredentialsUtil {

    public List<EmployeeCredentials> encryptPassword(List<EmployeeCredentials> credentials) {
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 12);
        credentials.forEach(credential -> {
            if (Objects.nonNull(credential) && !StringUtils.isEmpty(credential.getPassword())) {
                String password = bCryptPasswordEncoder.encode(credential.getPassword());
                credential.setPassword(password);
            }
        });
        return credentials;
    }
}

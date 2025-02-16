package com.employee.management.system.www.repository;

import com.employee.management.system.www.model.EmployeeCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends JpaRepository<EmployeeCredentials, Integer> {
}

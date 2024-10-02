package com.employee.management.system.www.repository;


import com.employee.management.system.www.model.FinanceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceDetailsRepository extends JpaRepository<FinanceDetails, Integer> {
}

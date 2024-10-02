package com.employee.management.system.www.model;

import lombok.Data;

@Data
public class EmployeeInput extends Employee {


    private String firstName;


    private String lastName;


    private int age;

    private String companyName;


    private String email;


    private String avatar;


    private FinancialDetailsInput financialDetails;
}

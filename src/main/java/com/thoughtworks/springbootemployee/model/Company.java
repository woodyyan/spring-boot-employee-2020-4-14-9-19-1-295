package com.thoughtworks.springbootemployee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Company {
    private Integer id;
    private String companyName;
    private Integer employeeNumber;
    private List<Employee> employees;
}

package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    @PostConstruct
    private void addEmployees() {
        employees.add(new Employee(0, "Xiaoming", 20, "Male"));
        employees.add(new Employee(1, "Xiaohong", 19, "Male"));
        employees.add(new Employee(2, "Xiaozhi", 15, "Male"));
        employees.add(new Employee(3, "Xiaogang", 16, "Male"));
        employees.add(new Employee(4, "Xiaoxia", 15, "Male"));
    }

    public List<Employee> findAllEmployee() {
        return employees;
    }

    public Employee findEmployeeById(Integer employeeId) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }
}

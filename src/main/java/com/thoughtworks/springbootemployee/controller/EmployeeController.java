package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.add(new Employee(0, "Woody", 20, "Male", 0));
        employees.add(new Employee(1, "Kit", 19, "Male", 0));
        employees.add(new Employee(2, "Hilary", 15, "Female", 0));
        employees.add(new Employee(3, "Leo", 16, "Male", 0));
        employees.add(new Employee(4, "Jay", 15, "Male", 0));
    }

    @GetMapping
    public List<Employee> getAll() {
        return employees;
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getPaginatedAll(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer pageSize
    ) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min((fromIndex + pageSize), employees.size());
        return employees.subList(fromIndex, toIndex);
    }

    @GetMapping(params = "gender")
    public List<Employee> getByGender(@RequestParam String gender) {
        return employees.stream()
            .filter(employee -> employee.getGender().toLowerCase().equals(gender.toLowerCase()))
            .collect(Collectors.toList());
    }

    @GetMapping("/{employeeId}")
    public Employee get(@PathVariable Integer employeeId) {
        return employees.stream()
            .filter(employee -> employee.getId().equals(employeeId))
            .findFirst()
            .orElse(null);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Integer employeeId) {
        Employee deletingEmployee = employees.stream()
            .filter(employee -> employee.getId().equals(employeeId))
            .findFirst()
            .orElse(null);
        employees.remove(deletingEmployee);
    }

    @PutMapping("/{employeeId}")
    public Employee update(@PathVariable Integer employeeId, @RequestBody Employee employee) {
        Employee updatingEmployee = employees.stream()
            .filter(employeeItem -> employeeItem.getId().equals(employeeId))
            .findFirst()
            .orElse(null);
        if (updatingEmployee != null) {
            if (employee.getName() != null) {
                updatingEmployee.setName(employee.getName());
            }
            if (employee.getAge() != null) {
                updatingEmployee.setAge(employee.getAge());
            }
            if (employee.getGender() != null) {
                updatingEmployee.setGender(employee.getGender());
            }
            if (employee.getSalary() != null) {
                updatingEmployee.setSalary(employee.getSalary());
            }
        }
        return updatingEmployee;
    }
}

package com.codegym.hotel.service;

import com.codegym.hotel.model.Employee;

public interface EmployeeService {
    Iterable<Employee> findAll();
    Employee findById(String id);
    void save(Employee employee);
    void deleteById(String id);
    Iterable<Employee> customFind(String query);
}

package com.codegym.hotel.repository;

import com.codegym.hotel.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String> {
    Iterable<Employee> findByIdContainsOrNameContainsAllIgnoreCase(String id, String name);
}
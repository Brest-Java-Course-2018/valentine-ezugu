package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.api.EmployeeService;
import org.springframework.dao.DataAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

public class EmployeeRestConsumer implements EmployeeService {

    public String url;

    RestTemplate restTemplate;

    public EmployeeRestConsumer(String url,  RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Employee saveEmployee(Employee employee) throws DataAccessException {
        return null;
    }

    @Override
    public Collection<Employee> getAllEmployees() throws DataAccessException {
        return null;
    }

    @Override
    public void deleteEmployeeById(Integer id) throws DataAccessException {

    }

    @Override
    public Employee getEmployeeById(Integer id) throws DataAccessException {
        return null;
    }

    @Override
    public void update(Employee employee) throws DataAccessException {

    }

}

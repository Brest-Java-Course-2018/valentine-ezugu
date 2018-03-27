package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-test.xml")
public class EmployeeRestConsumerMock {


    private static Employee employee;

    private static Employee employee1;


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        employee = new Employee("CEO",500, 2);
        employee1 = new Employee("Bulus", 500, 1);

        employee = new Employee("Val", 500, 2);
        employee.setEmployeeId(3);
    }

    @After
    public void tearDown() {
        Mockito.reset(restTemplate);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getAllEmployees() {
        List employees = Arrays.asList(new Employee("VINTO", 500, 2));

        ResponseEntity entity = new ResponseEntity(employees, HttpStatus.FOUND);

        when(restTemplate.getForEntity("http://localhost:8088/employees", List.class))
                .thenReturn(entity);

        Collection<Employee> results
                = employeeService.getAllEmployees();

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Mockito.verify(restTemplate).getForEntity("http://localhost:8088/employees", List.class);
    }


    @Test
    public void getEmployeeById() {
        ResponseEntity entity = new ResponseEntity<>(employee, HttpStatus.FOUND);

        when(restTemplate.getForEntity(Matchers.anyString(), Matchers.anyObject()))
                .thenReturn(entity);

        Employee result = employeeService.getEmployeeById(3);

        Assert.assertNotNull(result);
        Assert.assertEquals("Val", result.getEmployeeName());

        Mockito.verify(restTemplate).getForEntity(Matchers.anyString(), Matchers.anyObject());
    }

    @Test
    public void addEmployee() {
        ResponseEntity entity = new ResponseEntity<>(employee, HttpStatus.FOUND);
        when(restTemplate.postForEntity(Matchers.anyString(),
                Matchers.anyObject(), Matchers.anyObject()))
                .thenReturn(entity);

        Employee result = employeeService.saveEmployee(employee);

        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.getEmployeeId().intValue());

        Mockito.verify(restTemplate).postForEntity(Matchers.anyString(),
                Matchers.anyObject(),Matchers.anyObject());
    }

    @Test
    public void updateEmployee() {
        restTemplate.put("http://localhost:8088/employees", employee);
        Mockito.verify(restTemplate).put(Matchers.anyString(), Matchers.anyObject());
    }

    @Test
    public void deleteEmployee() {
        restTemplate.delete("http://localhost:8088/employees/1");
        Mockito.verify(restTemplate).delete(Matchers.anyString());
    }
}

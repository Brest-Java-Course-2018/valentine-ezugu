package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.DepartmentAvgSalary;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
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
public class DepartmentRestConsumerMock {

    private static DepartmentAvgSalary departmentAvgSalary;
    private static DepartmentAvgSalary departmentAvgSalary1;
    private static Department department;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        departmentAvgSalary = new DepartmentAvgSalary(1, "CEO", 500);
        departmentAvgSalary1 = new DepartmentAvgSalary(2, "JAVA", 500);

        department = new Department("CEO", "DESCRIPTION");
        department.setDepartmentId(3);
    }

    @After
    public void tearDown() {
        Mockito.reset(restTemplate);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getAllDepartmentDto() {
        List departments = Arrays.asList(new DepartmentAvgSalary(1, "Java",500));

        ResponseEntity entity = new ResponseEntity(departments, HttpStatus.FOUND);

        when(restTemplate.getForEntity("http://localhost:8088/departments", List.class))
                .thenReturn(entity);

        Collection<DepartmentAvgSalary> results
                = departmentService.getDepartmentsAvgSalary();

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Mockito.verify(restTemplate).getForEntity("http://localhost:8088/departments", List.class);
    }

    @Test
    public void getAllDepartments() {
        List departments = Arrays.asList(departmentAvgSalary, departmentAvgSalary1);
        ResponseEntity entity = new ResponseEntity<>(departments, HttpStatus.OK);

        when(restTemplate.getForEntity(Matchers.anyString(), Matchers.anyObject()))
                .thenReturn(entity);

        Collection<DepartmentAvgSalary> results
                = departmentService.getDepartmentsAvgSalary();

        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.size());
    }

    @Test
    public void getDepartmentById() {
        ResponseEntity entity = new ResponseEntity<>(department, HttpStatus.FOUND);

        when(restTemplate.getForEntity(Matchers.anyString(), Matchers.anyObject()))
                .thenReturn(entity);

        Department result = departmentService.getDepartmentById(3);

        Assert.assertNotNull(result);
        Assert.assertEquals("CEO", result.getDepartmentName());

        Mockito.verify(restTemplate).getForEntity(Matchers.anyString(), Matchers.anyObject());
    }

    @Test
    public void addDepartment() {
        ResponseEntity entity = new ResponseEntity<>(department, HttpStatus.FOUND);
        when(restTemplate.postForEntity(Matchers.anyString(),
                Matchers.anyObject(), Matchers.anyObject()))
                .thenReturn(entity);

        Department result = departmentService.saveDepartment(department);

        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.getDepartmentId().intValue());

        Mockito.verify(restTemplate).postForEntity(Matchers.anyString(),
                Matchers.anyObject(),Matchers.anyObject());
    }

    @Test
    public void updateDepartment() {
        restTemplate.put("http://localhost:8088/departments", department);
        Mockito.verify(restTemplate).put(Matchers.anyString(), Matchers.anyObject());
    }

    @Test
    public void deleteDepartment() {
        restTemplate.delete("http://localhost:8088/departments/1");
        Mockito.verify(restTemplate).delete(Matchers.anyString());
    }
}

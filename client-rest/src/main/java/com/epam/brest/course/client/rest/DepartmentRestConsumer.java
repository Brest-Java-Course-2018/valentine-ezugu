package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.DepartmentAvgSalary;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.api.DepartmentService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

public class DepartmentRestConsumer implements DepartmentService {

    private String url;

    private RestTemplate restTemplate;

    public DepartmentRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Department saveDepartment(Department department) throws DataAccessException {
       ResponseEntity responseEntity =  restTemplate.postForEntity(url, department, Department.class);
        Department result = (Department) responseEntity.getBody();
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Department> getAllDepartments() throws DataAccessException {
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        List<Department> departments = (List<Department>)responseEntity.getBody();
        responseEntity.getBody();
        return departments;
    }

    @Override
    public void deleteDepartmentById(Integer id) throws DataAccessException {

    }

    @Override
    public Department getDepartmentById(Integer id) throws DataAccessException {
        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(url + "/" + id, Department.class);
        Department departments =  responseEntity.getBody();
        responseEntity.getBody();
        return  departments;
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public Collection<DepartmentAvgSalary> getDepartmentsAvgSalary() {
        return null;
    }
}

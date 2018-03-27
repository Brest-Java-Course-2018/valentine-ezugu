package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.DepartmentAvgSalary;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

/**
 * rest consumer for department.
 */
public class DepartmentRestConsumer implements DepartmentService {
    /**
     * url.
     */
    private String url;
    /**
     * rest template.
     */
    private RestTemplate restTemplate;

    /**
     *
     * @param url1 .
     * @param restTemplate1 .
     */
    public DepartmentRestConsumer(final String url1,
                                  final RestTemplate restTemplate1) {
        this.url = url1;
        this.restTemplate = restTemplate1;
    }

    /**
     *
     * @param department for adding new Department.
     * @return dept.
     * @throws DataAccessException .
     */
    @Override
    public final Department saveDepartment(final Department department)
            throws DataAccessException {
       ResponseEntity<Department> responseEntity =
               restTemplate.postForEntity(url, department, Department.class);
        Department result =  responseEntity.getBody();
        return result;
    }

    /**
     *
     * @return list.
     * @throws DataAccessException .
     */
    @Override
    @SuppressWarnings("unchecked")
    public final List<Department> getAllDepartments()
            throws DataAccessException {
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, List.class);
        List<Department> departments =
                (List<Department>) responseEntity.getBody();
        return departments;
    }

    /**
     *
     * @param id for deleteBy Id.
     * @throws DataAccessException .
     */
    @Override
    public final void deleteDepartmentById(final Integer id)
            throws DataAccessException {
        restTemplate.delete(url + "/" + id);
    }

    /**
     *
     * @param id for get by Id.
     * @return department.
     * @throws DataAccessException .
     */
    @Override
    public final Department getDepartmentById(final Integer id)
            throws DataAccessException {
        ResponseEntity<Department> responseEntity =
                restTemplate.getForEntity(url + "/" + id, Department.class);
        Department department =  responseEntity.getBody();
        return  department;
    }

    /**
     *
     * @param department for update.
     */
    @Override
    public final void updateDepartment(final Department department) {
        restTemplate.put(url, department);
    }

    /**
     *
     * @return list of departments with avg.
     */
    @Override
    @SuppressWarnings("unchecked")
    public final Collection<DepartmentAvgSalary> getDepartmentsAvgSalary() {
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, List.class);
        List<DepartmentAvgSalary> departments =
                (List<DepartmentAvgSalary>) responseEntity.getBody();
        responseEntity.getBody();
        return departments;
     }

}

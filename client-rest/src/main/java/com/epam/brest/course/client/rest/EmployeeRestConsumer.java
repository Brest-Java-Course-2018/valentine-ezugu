package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

/**
 * restfull web service.
 */
public class EmployeeRestConsumer implements EmployeeService {
    /**
     * url.
     */
    private String url;
    /**'
     * template rest.
     */
    private RestTemplate restTemplate;

    /**
     *
     * @return url.
     */
    public final String getUrl() {
        return url;
    }

    /**
     *
     * @param url2 .
     */
    public final void setUrl(final String url2) {
        this.url = url2;
    }

    /**
     *
     * @param url1 .
     * @param restTemplate1 .
     */
    public EmployeeRestConsumer(final String url1,
                                final RestTemplate restTemplate1) {
        this.url = url1;
        this.restTemplate = restTemplate1;
    }

    /**
     *
     * @param employee for adding new employee.
     * @return saved employee.
     * @throws DataAccessException .
     */
    @Override
    public final Employee saveEmployee(final Employee employee)
            throws DataAccessException {
        ResponseEntity<Employee> entity =
                restTemplate.postForEntity(url, employee, Employee.class);
        Employee  result =   entity.getBody();
        return result;
    }

    /**
     *
     * @return list.
     * @throws DataAccessException .
     */
    @Override
    @SuppressWarnings("unchecked")
    public final Collection<Employee> getAllEmployees()
            throws DataAccessException {
        ResponseEntity entity = restTemplate.getForEntity(url, List.class);
        List<Employee> employees = (List<Employee>) entity.getBody();
        return employees;
    }

    /**
     *
     * @param id for deleteBy Id.
     * @throws DataAccessException
     */
    @Override
    public final void deleteEmployeeById(final Integer id)
            throws DataAccessException {
     restTemplate.delete(url + "/" + id);
    }

    /**
     *
     * @param id for get by Id.
     * @return one employee.
     * @throws DataAccessException .
     */
    @Override
    public final Employee getEmployeeById(final Integer id)
            throws DataAccessException {
        ResponseEntity<Employee> entity =
                restTemplate.getForEntity(url + "/" + id, Employee.class);
        Employee employee =  entity.getBody();
        return employee;
    }

    /**
     *
     * @param employee for update.
     * @throws DataAccessException .
     */
    @Override
    public final void update(final Employee employee)
            throws DataAccessException {
        restTemplate.put(url, employee);
    }

}

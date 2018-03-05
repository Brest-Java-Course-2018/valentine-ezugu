package com.epam.brest.course.api;

import com.epam.brest.course.Employee;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 *service class
 */
public interface EmployeeService {
    /**
     *
     * @param employee for adding new employee
     * @return new employee
     */
    Employee addEmployee(Employee employee) throws DataAccessException;

    /**
     *
     * @return a list of employees
     */
    List<Employee> getAllEmployees() throws DataAccessException;

    /**
     *
     * @param id for deleteBy Id
     */
    void deleteEmployeeById(Integer id) throws DataAccessException;

    /**
     *
     * @param id for get by Id
     * @return found employee
     */
    Employee getEmployeeById(Integer id) throws DataAccessException;

}



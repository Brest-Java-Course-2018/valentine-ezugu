package com.epam.brest.course.service;

import com.epam.brest.course.model.Employee;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 *service class.
 */

public interface EmployeeService {

    /**
     *@throws DataAccessException if db cant be accessed.
     * @param employee for adding new employee.
     * @return new employee.
     */
    Employee saveEmployee(Employee employee) throws DataAccessException;

    /**
     *@throws DataAccessException if db cant be accessed.
     * @return a list of employees.
     */
    Collection<Employee> getAllEmployees() throws DataAccessException;

    /**
     *
     * @param id for deleteBy Id.
     * @throws DataAccessException if db cant be accessed.
     */
    void deleteEmployeeById(Integer id) throws DataAccessException;

    /**
     *
     * @param id for get by Id.
     * @return found employee.
     * @throws DataAccessException if db cant be accessed.
     */
    Employee getEmployeeById(final Integer id) throws DataAccessException;

    /**
     *
     * @param employee for update.
     * @throws DataAccessException when occurred.
     */
      void update(Employee employee) throws DataAccessException;
}



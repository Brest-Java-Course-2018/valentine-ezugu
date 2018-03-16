package com.epam.brest.course.api;

import com.epam.brest.course.Employee;
import org.springframework.dao.DataAccessException;

import java.util.List;

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
    List<Employee> getAllEmployees() throws DataAccessException;

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
    Employee getEmployeeById(Integer id) throws DataAccessException;

}



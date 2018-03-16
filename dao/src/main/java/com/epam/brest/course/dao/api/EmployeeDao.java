package com.epam.brest.course.dao.api;

import com.epam.brest.course.model.Employee;

import java.util.List;

/**
 * employee dao class.
 *
 * @author user.
 * @version $Id: $.
 */
public interface EmployeeDao {


    /**
     * <p>getEmployees</p>.
     *
     * @return a list of employees.
     */
    List<Employee> getEmployees();

    /**
     * <p>getEmployeeById</p>.
     *
     * @param id to select a particular id.
     * @return the Employee selected.
     */
    Employee getEmployeeById(Integer id);

    /**
     * <p>addEmployee</p>.
     *
     * @param employee takes an object as param for adding new.
     * employee.
     * @return employee.
     */
    Employee addEmployee(Employee employee);

    /**
     * <p>updateEmployee</p>.
     *
     * @param employee object used for update.
     */
    void updateEmployee(Employee employee);

    /**
     * <p>deleteEmployeeById</p>.
     *
     * @param id for delete.
     */
    void deleteEmployeeById(Integer id);


  }



package com.epam.brest.course.dao.api;

import com.epam.brest.course.Employee;
import java.util.List;

public interface EmployeeDao {

    /**
     *
     * @return a list of employees.
     */
    List<Employee> getEmployees();

    /**
     *
     * @param id to select a particular id.
     * @return the Employee selected.
     */
    Employee getEmployeeById(Integer id);

    /**
     *
     * @param employee takes an object as param for adding new.
     * employee.
     * @return employee.
     */
    Employee addEmployee (Employee employee);

    /**
     *
     * @param employee object used for update.
     */
    void updateEmployee(Employee employee);

    /**
     *
     * @param id for delete.
     */
    void deleteEmployeeById(Integer id);


  }



package com.epam.brest.course.service.impl;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.api.EmployeeService;
import com.epam.brest.course.dao.api.EmployeeDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;


/**
 * This is the service bean for employee entity
 * I have not seperated services  into two distinct modules.
 * because its only two entities.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {


    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * dao for db transactions.
     */
    private EmployeeDao employeeDao;

    /**
     *
     * @param employeeDao1 for setter inject.
     */
    @Autowired
    public final void setEmployeeDao(final EmployeeDao employeeDao1) {
        this.employeeDao = employeeDao1;
    }

    /**
     *
     * @param employee for adding new employee.
     * @return new employee.
     * @throws DataAccessException when cannot access db.
     */
    @Override
    public final Employee saveEmployee(final Employee employee)
            throws DataAccessException {
        LOGGER.debug("saveEmployee({})", employee);

        Assert.notNull(employee, "employee cannot be null");
        Assert.notNull(employee.getEmployeeId(), "id cannot be null");
        Assert.hasText(employee.getEmployeeName(), "name cannot be null");
        Assert.notNull(employee.getSalary(), "salary cannot be null");
        Assert.hasText(employee.getEmail(), "you should have email");
        Assert.notNull(employee.getDepartmentId(),
                "department id cannot be null");
        return employeeDao.addEmployee(employee);
    }

    /**
     *
     * @return list of all employees.
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public final Collection<Employee> getAllEmployees()
            throws DataAccessException {
        LOGGER.debug("getAllEmployees()");
        return employeeDao.getEmployees();
    }

    /**
     * @param id for deleteBy Id.
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public final void deleteEmployeeById(final Integer id)
            throws DataAccessException {
        LOGGER.debug("deleteEmployeeById({})", id);
        employeeDao.deleteEmployeeById(id);
    }

    /**
     *
     * @param id for get by Id.
     * @return an employee that matches id.
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public final Employee getEmployeeById(final Integer id)
            throws DataAccessException {
            LOGGER.debug("getEmployeeById({})", id);
            Assert.notNull(id, "id cannot be null");
        return employeeDao.getEmployeeById(id);
    }

    /**
     *
     * @param employee update.
     * @throws DataAccessException exceptions.
     */
    @Override
    public final void update(final Employee employee)
            throws DataAccessException {

        Assert.notNull(employee, "employee cannot be null");
        LOGGER.debug("update({})", employee);
          employeeDao.updateEmployee(employee);
    }
}

package com.epam.brest.course.impl;

import com.epam.brest.course.Employee;
import com.epam.brest.course.api.EmployeeService;
import com.epam.brest.course.dao.api.EmployeeDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * This is the service bean for employee entity
 * I have not seperated services  into two distinct modules.
 * because its only two entities.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * dao for db transactions.
     */
    @Autowired
    private EmployeeDao employeeDao;

    /**
     *
     * @param employee for adding new employee.
     * @return new employee.
     * @throws DataAccessException when cannot access db.
     */
    @Override
    public Employee saveEmployee(Employee employee) throws DataAccessException{
        LOGGER.debug("saveEmployee({})", employee);

        Assert.notNull(employee, "employee cannot be null");

        Assert.hasText(employee.getEmployeeName(),"name cannot be null");
        Assert.notNull(employee.getSalary(),"salary cannot be null");

        Assert.notNull(employee.getDepartmentId(),"department id cannot be null");

        return employeeDao.addEmployee(employee);
    }

    /**
     *
     * @return list of all employees.
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public List<Employee> getAllEmployees() throws DataAccessException {
        LOGGER.debug("getAllEmployees()");

        return employeeDao.getEmployees();
    }

    /**
     *
     * @param id for deleteBy Id.
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public void deleteEmployeeById(Integer id) throws DataAccessException {
        LOGGER.debug("deleteEmployeeById({})" ,id);

        employeeDao.deleteEmployeeById(id);
    }

    /**
     *
     * @param id for get by Id.
     * @return an employee that matches id ;
     * @throws DataAccessException DataAccessException when cannot access db.
     */
    @Override
    public Employee getEmployeeById(Integer id) throws DataAccessException {
        return employeeDao.getEmployeeById(id);
    }


}

package com.epam.brest.course.impl;

import com.epam.brest.course.Employee;
import com.epam.brest.course.api.EmployeeService;
import com.epam.brest.course.dao.api.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * This is the service class for employee entity
 * I have not seperated services  into two distinct modules
 * because its only two entities.
 *  Todo @Transactional and @Services will be added in xml for now
 *  TODO @EnableTransactionManagement will also be added
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee addEmployee(Employee employee) throws DataAccessException{
         Assert.notNull(employee, "employee cannot be null");

         Assert.hasText(employee.getEmployeeName(),"name cannot be null");
         Assert.notNull(employee.getSalary(),"salary cannot be null");

         Assert.notNull(employee.getDepartmentId(),"department id cannot be null");

        return employeeDao.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() throws DataAccessException {
        return employeeDao.getEmployees();
    }

    @Override
    public void deleteEmployeeById(Integer id) throws DataAccessException {
        employeeDao.deleteEmployeeById(id);
    }

    @Override
    public Employee getEmployeeById(Integer id) throws DataAccessException {
        return employeeDao.getEmployeeById(id);
    }
}

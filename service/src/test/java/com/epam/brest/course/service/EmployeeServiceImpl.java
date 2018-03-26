package com.epam.brest.course.service;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.api.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:bean.xml", "classpath:dao.xml"})
@Transactional
@Rollback
public class EmployeeServiceImpl {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int ID = 1;
    private static final String EMPLOYEE_NAME = "valentine";
    private static final String EMAIL = "valen@yahoo.com";

    private static final String EMPLOYEE_NAME_FOR_UPDATE = "Enterprise Integration Pattern";
    private static final int EMPLOYEE_ID = 22;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void saveEmployee() throws Exception {
        LOGGER.debug("test: saveEmployee()");
        Collection<Employee> employees = employeeService.getAllEmployees();
        int sizeBeforeAdd = employees.size();

        //populating the employee for add
        Employee employee = new Employee();
        employee.setEmployeeId(EMPLOYEE_ID);
        employee.setEmployeeName(EMPLOYEE_NAME);
        employee.setSalary(900);
        employee.setDepartmentId(ID);
        employee.setEmail(EMAIL);

        //perform save
        Employee newEmployee = employeeService.saveEmployee(employee);

        //assertions
        Assert.assertNotNull(employee.getEmployeeId());
        Assert.assertNotNull(newEmployee.getEmployeeId());
        Assert.assertTrue(newEmployee.getEmployeeName().equals(employee.getEmployeeName()));
        Assert.assertTrue(newEmployee.getEmail().matches("valen@yahoo.com"));
        Assert.assertTrue(newEmployee.getSalary().equals(employee.getSalary()));
        Assert.assertTrue(sizeBeforeAdd < employeeService.getAllEmployees().size());
        Assert.assertTrue((sizeBeforeAdd + 1) == employeeService.getAllEmployees().size());

    }

    @Test
    public void getEmployeeById() throws Exception {
        LOGGER.debug("test: getEmployeeById()");

        Employee employee = new Employee();
        employee.setEmployeeName(EMPLOYEE_NAME);
        employee.setEmail(EMAIL);

        //get this employee by id
        Employee empl = employeeService.getEmployeeById(ID);

        //assertions
        Assert.assertNotNull(employee);
        Assert.assertEquals("valentine", empl.getEmployeeName());
    }

    @Test
    public void updateEmployee() throws Exception {
        LOGGER.debug("test: updateEmployee()");

        //get employee by id for updating
        Employee employee = employeeService.getEmployeeById(ID);

        //confirm we got the right employee
        Assert.assertEquals(employee.getEmployeeName(), "valentine");

        //give employee a new name
        employee.setEmployeeName(EMPLOYEE_NAME_FOR_UPDATE);
        employeeService.update(employee);

        // assert to confirm result
        Assert.assertNotNull(employee);
        Assert.assertEquals(EMPLOYEE_NAME_FOR_UPDATE, employee.getEmployeeName());
    }

    @Test
    public void deleteById() throws Exception {
        LOGGER.debug("test: deleteById()");
        int employeeSize = employeeService.getAllEmployees().size();

        //get this employee by id and delete
        employeeService.deleteEmployeeById(ID);

        //assertions
        Assert.assertTrue(employeeSize > employeeService.getAllEmployees().size());
    }

}

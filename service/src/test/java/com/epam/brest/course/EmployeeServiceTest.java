package com.epam.brest.course;

import com.epam.brest.course.api.EmployeeService;
import com.epam.brest.course.config.TestConfig;
import com.epam.brest.course.dao.api.EmployeeDao;
import com.epam.brest.course.impl.EmployeeServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={ EmployeeServiceImpl.class, TestConfig.class})
public class EmployeeServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int ID = 1;

    /**
     * for Exception Tests.
     */
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeService employeeService;

    /**
     *
     * @throws Exception in case of rule violation.
     */
    @Test
    public void addEmployee() throws Exception {
        LOGGER.debug("test: addEmployee()");

        Employee employee = new Employee();
        employee.setEmployeeName("valentine");
        employee.setSalary(400);
        employee.setDepartmentId(ID);

        employee.setEmployeeId(ID);
        Employee employee1;
        when(employeeDao.addEmployee(employee)).thenReturn(employee);
        employee1 = employeeService.saveEmployee(employee);

        org.junit.Assert.assertNotNull(employee);
        assertEquals(employee1.getSalary(), employee.getSalary());
        Mockito.verify(employeeDao).addEmployee(employee);

    }

    /**
     *
     * @throws Exception in case of rule violation.
     */
    @Test
    public void getEmployeeById() throws Exception {
        LOGGER.debug("test: getEmployeeById()");

        Employee employee = new Employee();
        employee.setEmployeeId(ID);
        when(employeeDao.getEmployeeById(Mockito.anyInt()))
                .thenReturn(employee);

        Employee employee1 = employeeService.getEmployeeById(ID);
        assertEquals(employee.getEmployeeId(), employee1.getEmployeeId());
        Mockito.verify(employeeDao).getEmployeeById(Mockito.anyInt());

    }

    /**
     *
     * @throws Exception in case of rule violation.
     */
    @Test
    public void deleteEmployeeById() throws Exception {
        LOGGER.debug("test: deleteEmployeeById()");

        Employee employee = new Employee();
        employee.setEmployeeId(ID);
        employeeService.deleteEmployeeById(ID);
        Mockito.verify(employeeDao).deleteEmployeeById(ID);

    }

    /**
     *
     * @throws Exception in case of rule violation.
     */
    @Test
    public void getEmployeeList() throws Exception {
        LOGGER.debug("test: getEmployeeList()");
        Employee employee = new Employee();
            employee.setDepartmentId(ID);
            Employee employee1 = new Employee();
            employee.setDepartmentId(ID);

            when(employeeDao.getEmployees()).thenReturn(Arrays.asList(employee,employee1));

            employeeService.getAllEmployees();
            Mockito.verify(employeeDao).getEmployees();
    }

    /**
     *
     * @throws Exception is thrown because id cannot be null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionOccursWhenIncompleteArgument() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeName("ana");
        employeeService.saveEmployee(employee);
    }


}

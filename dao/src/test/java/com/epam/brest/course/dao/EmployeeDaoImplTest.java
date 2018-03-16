package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.dao.api.EmployeeDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db-spring.xml",
        "classpath:test-dao.xml","classpath:dao.xml"})
@Rollback
@Transactional
public class EmployeeDaoImplTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        Assert.assertFalse(employees.isEmpty());
    }

    /**
     * get Employee by id test
     * test if we can get Employee from db.
     */
    @Test
    public void getEmployeeById() {
        Employee employee = employeeDao.getEmployeeById(1);
        Assert.assertNotNull(employee);
        Assert.assertTrue(employee.getDepartmentId().equals(1));
        Assert.assertTrue(employee.getEmployeeName().equals("valentine"));
        Assert.assertTrue(employee.getSalary().equals(400));
    }

    /**
     * add document test.
     */
    @Test
    public void addEmployee() {
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeName("salisu");
        testEmployee.setSalary(400);
        testEmployee.setDepartmentId(1);
        Employee resultEmployee = employeeDao.addEmployee(testEmployee);
        Assert.assertNotNull(resultEmployee);
        Assert.assertTrue(testEmployee.equals(resultEmployee));
        Assert.assertEquals(testEmployee.getEmployeeName(),resultEmployee.getEmployeeName());

    }

    /**
     * At first i get Employee by id to confirm deptName.
     * now set new Employee name and update.
     * now call getEmployeeBy id to prove update has occurred.
     */
    @Test
    public void updateEmployee() {
        Employee employee = employeeDao.getEmployeeById(1);
        Assert.assertEquals("valentine",employee.getEmployeeName());
        Assert.assertNotNull(employee);

        employee.setEmployeeId(1);
        employee.setEmployeeName("james");
        employee.setSalary(500);

        employeeDao.updateEmployee(employee);

        Employee finalResult = employeeDao.getEmployeeById(1);
        Assert.assertNotNull("james",finalResult.getEmployeeName());
        Assert.assertNotNull(employee);
    }

    /**
     *EmptyResultDataAccessException
     * works by checking that the expected size is 0
     * if true then such exception is thrown
     * because delete operation has a void return type
     * when we getEmployeeById value returned will be 0
     * we cant return null because null means variable that points to nothing
     * and in our case getEmployeeById will return exception because we no such id.
     */
    @Test(expected = org.springframework.dao.EmptyResultDataAccessException.class)
    public void deleteEmployee() {
        Employee employee1 = new Employee();
        employee1.setEmployeeName("Distribution");
        employee1.setEmployeeId(5);
        Employee employee = employeeDao.getEmployeeById(5);
        Assert.assertEquals("Distribution", employee.getEmployeeName());
        employeeDao.deleteEmployeeById(5);
    }

}

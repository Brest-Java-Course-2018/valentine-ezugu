package com.brest.course;

import com.epam.brest.course.Employee;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeTest {

    public static final String VALIK ="valik";

    @Test
    public void getEmployeeName( ) {

        Employee employee = new Employee();
        employee.setEmployeeName(VALIK);
        Assert.assertTrue(employee.getEmployeeName().equals(VALIK));
        Assert.assertEquals(VALIK,employee.getEmployeeName());
    }
}

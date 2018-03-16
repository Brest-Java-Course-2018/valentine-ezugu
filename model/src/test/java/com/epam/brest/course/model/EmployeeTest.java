package com.epam.brest.course.model;

import com.epam.brest.course.model.Employee;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeTest {

    private static final String VALIK ="valik";

    @Test
    public void getEmployeeName( ) {

        Employee employee = new Employee();
        employee.setEmployeeName(VALIK);
        Assert.assertTrue(employee.getEmployeeName().equals(VALIK));
        Assert.assertEquals(VALIK,employee.getEmployeeName());
    }
}

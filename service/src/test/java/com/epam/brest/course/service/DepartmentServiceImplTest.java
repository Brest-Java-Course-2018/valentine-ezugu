package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import com.epam.brest.course.service.EmployeeService;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:bean-test.xml", "classpath:dao.xml"})
@Transactional
@Rollback
public class DepartmentServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int ID = 1;
    private static final String DEPARTMENT_NAME = "Academic";
    private static final String BOSS_MAN = "Siarhei";
    private static final String DESCRIPTION = "Study learn Jms and osgi";

    private static final String DEPARTMENT_NAME_FOR_UPDATE = "Enterprise Integration Pattern";
    private static final int DEPARTMENT_ID = 22;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void saveDepartment() throws Exception {
        LOGGER.debug("test: saveDepartment()");
        List<Department> departments = departmentService.getAllDepartments();
        int sizeBeforeAdd = departments.size();

        //populating the department for add
        Department department = new Department();
        department.setDepartmentId(DEPARTMENT_ID);
        department.setDepartmentName(DEPARTMENT_NAME);
        department.setHeadOfDepartment(BOSS_MAN);
        department.setDescription(DESCRIPTION);

        //perform save
        Department newDepartment = departmentService.saveDepartment(department);

        //assertions
        Assert.assertNotNull(department.getDepartmentId());
        Assert.assertNotNull(newDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentName().equals(department.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDescription().equals(department.getDescription()));
        Assert.assertTrue(sizeBeforeAdd < departmentService.getAllDepartments().size());
        Assert.assertTrue((sizeBeforeAdd + 1) == departmentService.getAllDepartments().size());

    }


    @Test
    public void getDepartmentById()throws Exception {
        LOGGER.debug("test: getDepartmentById()");

        //populating the department
        Department department = new Department();
        department.setDepartmentName(DEPARTMENT_NAME);
        department.setHeadOfDepartment(BOSS_MAN);
        department.setDescription(DESCRIPTION);

        //get this department by id
        Department dept_test = departmentService.getDepartmentById(ID);

        //assertions
        Assert.assertNotNull(department);
        Assert.assertEquals("Warren Buffet", dept_test.getHeadOfDepartment());
    }


    @Test
    public void updateDepartment() throws Exception {
        LOGGER.debug("test: updateDepartment()");

        //get department by id for updating
        Department department = departmentService.getDepartmentById(ID);

        //confirm we got the right department
        Assert.assertEquals(department.getDepartmentName(), "CEO Department");

        //give department a new name
        department.setDepartmentName(DEPARTMENT_NAME_FOR_UPDATE);
        departmentService.updateDepartment(department);

        // assert to confirm result
        Assert.assertNotNull(department);
        Assert.assertEquals(DEPARTMENT_NAME_FOR_UPDATE, department.getDepartmentName());
    }

    @Test
    public void deleteById() throws Exception {
        LOGGER.debug("test: deleteById()");
        int departmentSize = departmentService.getAllDepartments().size();
        //get this department by id and delete
        departmentService.deleteDepartmentById(ID);
        //assertions
        Assert.assertTrue(departmentSize > departmentService.getAllDepartments().size());
    }

}

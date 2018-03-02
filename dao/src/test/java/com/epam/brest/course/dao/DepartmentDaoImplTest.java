package com.epam.brest.course.dao;

import com.epam.brest.course.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db-spring.xml",
        "classpath:test-dao.xml"})
public class DepartmentDaoImplTest {
    /**
     * my dao for department ,
     * containing db connection and jdbc template
     */
    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void getDepartments() {
        List<Department> departments = departmentDao.getDepartments();
        Assert.assertFalse(departments.isEmpty());
    }

    /**
     * get department by id test
     * test if we can get department from db
     */
    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(1);
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(1));
        Assert.assertTrue(department.getDepartmentName().equals("Distribution"));
        Assert.assertTrue(department.getDescription().equals("Distribution Department"));
    }

    /**
     * add document test
     */
    @Test
    public void addDepartment() {
        Department testDepartment = new Department();
        testDepartment.setDepartmentName("ANGULAR AND UI");
        testDepartment.setDescription("FRONT-END DEVS");
        Department resultDepartment = departmentDao.addDepartment(testDepartment);
        Assert.assertNotNull(resultDepartment);
        Assert.assertTrue(testDepartment.equals(resultDepartment));
        Assert.assertEquals(testDepartment.getDepartmentName(),resultDepartment.getDepartmentName());

    }

    /**
     * At first i get department by id to confirm deptName
     * now set new department name and update
     * now call getDepartmentBy id to prove update has occurred
     */
    @Test
    public void updateDepartment() {
        Department department = departmentDao.getDepartmentById(1);
        Assert.assertEquals("Distribution",department.getDepartmentName());
        Assert.assertNotNull(department);

        department.setDepartmentId(1);
        department.setDepartmentName("Engineering");
         departmentDao.updateDepartment(department);

        Department finalResult = departmentDao.getDepartmentById(1);
        Assert.assertNotNull("Distribution",finalResult.getDepartmentName());
        Assert.assertNotNull(department);
    }

    /**
     *EmptyResultDataAccessException
     * works by checking that the expected size is 0
     * if true then such exception is thrown
     * because delete operation has a void return type
     * when we getDepartmentById value returned will be 0
     * we cant return null because null means variable that points to nothing
     * and in our case getDepartmentById will return exception because we no such id
     */
    @Test(expected = org.springframework.dao.EmptyResultDataAccessException.class)
    public void deleteDepartment() {
      Department department = departmentDao.getDepartmentById(1);
      Assert.assertEquals("Distribution", department.getDepartmentName());
      departmentDao.deleteDepartmentById(1);
      departmentDao.getDepartmentById(1);
    }

}


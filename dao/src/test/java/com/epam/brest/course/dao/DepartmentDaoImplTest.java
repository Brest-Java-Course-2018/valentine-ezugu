package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.dao.api.DepartmentDao;
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
        "classpath:test-dao.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class DepartmentDaoImplTest {
    /**
     * my dao for department ,
     * containing db connection and jdbc template.
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
     * test if we can get department from db.
     */
    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(1);
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(1));
        Assert.assertTrue(department.getDepartmentName().equals("CEO Department"));
        Assert.assertTrue(department.getDescription().equals("Department Of Chief executive officer"));
    }

    /**
     * add document test.
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
     * At first i get department by id to confirm deptName.
     * now set new department name and update.
     * now call getDepartmentBy id to prove update has occurred.
     */
    @Test
    public void updateDepartment() {
        Department department = departmentDao.getDepartmentById(1);
        Assert.assertEquals("CEO Department",department.getDepartmentName());
        Assert.assertNotNull(department);

        department.setDepartmentId(1);
        department.setDepartmentName("Engineering");
         departmentDao.updateDepartment(department);

        Department finalResult = departmentDao.getDepartmentById(1);
        Assert.assertNotNull("CEO Department",finalResult.getDepartmentName());
        Assert.assertNotNull(department);
    }

    /**
     *EmptyResultDataAccessException
     * works by checking that the expected size is 0
     * if true then such exception is thrown
     * because delete operation has a void return type
     * when we getDepartmentById value returned will be 0
     * we cant return null because null means variable that points to nothing
     * and in our case getDepartmentById will return exception because we no such id.
     */
    @Test
    public void deleteDepartment() {
        Department department1 = new Department();
        department1.setDepartmentName("Hr management'");
        department1.setDepartmentId(7);
        Department department = departmentDao.getDepartmentById(7);
        Assert.assertEquals("Hr management", department.getDepartmentName());
        departmentDao.deleteDepartmentById(7);
    }

     @Test
    public void addDeptSecondTest(){

        List<Department> departments = departmentDao.getDepartments();
        int sizeBefore = departments.size();
        Department department = new Department("education ","department java");
        Department newDepartment = departmentDao.addDepartment(department);
        Assert.assertNotNull(newDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentName().equals(department.getDepartmentName()));
        Assert.assertTrue(newDepartment.getDescription().equals(department.getDescription()));
        Assert.assertTrue(sizeBefore< departmentDao.getDepartments().size());
        Assert.assertTrue((sizeBefore +1) == departmentDao.getDepartments().size() );
    }


    @Test(expected = IllegalArgumentException.class)
    public void addSameDepartmentTest(){
        Department department =
                new Department("Education and Training", "Department Education");
        departmentDao.addDepartment(department);
        departmentDao.addDepartment(department);
    }

    @Test
    public void updateDeptSecondTest(){
        Department department =
                new Department("Education" , "Department of Education");

        Department newDepartment = departmentDao.addDepartment(department);
        newDepartment.setDepartmentName("NEWEducation");
        department.setDescription("NEW Department of Education");
        departmentDao.updateDepartment(newDepartment);

        Department updateDepartment = departmentDao.getDepartmentById(newDepartment.getDepartmentId());
        Assert.assertTrue(newDepartment.getDepartmentId().equals(updateDepartment.getDepartmentId()));
        Assert.assertTrue(newDepartment.getDepartmentName().equals(department.getDepartmentName()));


    }


}


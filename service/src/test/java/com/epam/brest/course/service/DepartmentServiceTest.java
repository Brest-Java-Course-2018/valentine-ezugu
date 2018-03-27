package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.config.MockConfig;
import com.epam.brest.course.dao.api.DepartmentDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
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
@ContextConfiguration(classes ={ DepartmentServiceImpl.class, MockConfig.class})
public class DepartmentServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int ID = 1;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentDao departmentDao;


    @Test
    public void addDepartment() throws Exception {
        LOGGER.debug("test: addDepartment()");

        Department department = new Department();
        department.setDepartmentName("java academy");
        department.setDepartmentId(ID);
        department.setDescription("Learn java");
        department.setHeadOfDepartment("val");


        Department testDepartment;
        when(departmentDao.addDepartment(department))
                .thenReturn(department);

        testDepartment = departmentService.saveDepartment(department);

        org.junit.Assert.assertNotNull(department);
        assertEquals(testDepartment.getDepartmentName(),
                department.getDepartmentName());
        Mockito.verify(departmentDao)
                .addDepartment(department);

    }

    /**
     *
     * @throws Exception in case of rule violation.
     */
    @Test
    public void getDepartmentById() throws Exception {
        LOGGER.debug("test: getDepartmentById()");

        Department department = new Department();
        department.setDepartmentId(ID);
        when(departmentDao.getDepartmentById(Mockito.anyInt()))
                .thenReturn(department);

        Department department1 = departmentService.getDepartmentById(ID);
        assertEquals(department.getDepartmentId(),
                department1.getDepartmentId());
        Mockito.verify(departmentDao)
                .getDepartmentById(Mockito.anyInt());

    }

    /**
     *
     * @throws Exception in case of rule violation.
     */
    @Test
    public void deleteDepartmentById() throws Exception {
        LOGGER.debug("test: deleteDepartmentById()");

        Department department = new Department();
        department.setDepartmentId(ID);
        departmentService.deleteDepartmentById(ID);
        Mockito.verify(departmentDao).deleteDepartmentById(ID);

    }

    /**
     *
     * @throws Exception in case of rule violation.
     */
    @Test
    public void getDepartmentList() throws Exception {
        LOGGER.debug("test: getDepartmentList()");
        Department department = new Department();
        department.setDepartmentId(ID);
        Department department1 = new Department();
        department.setDepartmentId(ID);

        when(departmentDao.getDepartments())
                .thenReturn(Arrays.asList(department,department1));

        departmentService.getAllDepartments();
        Mockito.verify(departmentDao).getDepartments();
    }

    /**
     *
     * @throws Exception is thrown because id cannot be null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionOccursWhenIncompleteArgumentIsGiven() throws Exception {
        Department department = new Department();
        department.setDepartmentName("ana");
        department.setHeadOfDepartment("mikel");
        departmentService.saveDepartment(department);

    }

}

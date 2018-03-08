package com.epam.brest.course;

import com.epam.brest.course.api.DepartmentService;
import com.epam.brest.course.config.TestConfig;
import com.epam.brest.course.dao.api.DepartmentDao;
import com.epam.brest.course.impl.DepartmentServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={ DepartmentServiceImpl.class, TestConfig.class})
public class DepartmentServiceTest {


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentDao departmentDao;


}

package com.epam.brest.course.service.config;

import com.epam.brest.course.dao.api.DepartmentDao;
import com.epam.brest.course.dao.api.EmployeeDao;
import com.epam.brest.course.service.impl.EmployeeServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean
    public EmployeeDao employeeDao(){
        return Mockito.mock(EmployeeDao.class);
    }

    @Bean
    public DepartmentDao departmentDao(){
        return Mockito.mock(DepartmentDao.class);
    }

}

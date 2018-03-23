package com.epam.brest.course.controller.web_app.config;

import com.epam.brest.course.service.api.DepartmentService;
import com.epam.brest.course.service.api.EmployeeService;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Profile("test")
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.epam.brest.course.service.web_app")
public class TestConfig {

    @Bean
    public EmployeeService employeeService(){
        return Mockito.mock(EmployeeService.class);
    }

    @Bean
    public DepartmentService departmentService(){
        return Mockito.mock(DepartmentService.class);
    }

}

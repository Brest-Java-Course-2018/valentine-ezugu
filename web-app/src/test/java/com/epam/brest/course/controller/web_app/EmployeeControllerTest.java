package com.epam.brest.course.controller.web_app;

import com.epam.brest.course.controllers.web_app.controllers.EmployeeController;

import com.epam.brest.course.model.Employee;

import com.epam.brest.course.service.DepartmentService;
import com.epam.brest.course.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:test-root-context.xml"})
public class EmployeeControllerTest {

    @Autowired
    EmployeeController employeeController;

    private Employee employeeForList;

    private Employee employeeForList1;

    private Employee employee;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");

        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .setViewResolvers(viewResolver)
                .build();

        employeeForList = new Employee();
        employeeForList.setEmployeeId(1);
        employeeForList.setDepartmentId(1);
        employeeForList.setSalary(400);
        employeeForList.setEmail("valentineEzugu@yahoo.com");

        employeeForList1 = new Employee();
        employeeForList1.setEmployeeId(2);
        employeeForList1.setDepartmentId(2);
        employeeForList1.setSalary(400);
        employeeForList1.setEmail("valentineEzugu@gmail.com");
        
        employee = new Employee();
        employee.setEmployeeId(22);
        employee.setDepartmentId(1);
        employee.setEmployeeName("Vlad");
        employee.setSalary(1000);
        employee.setEmail("vati@yahoo.com");
    }

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;



    @Test
    public void getEmployeeList() throws Exception {

        when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(employeeForList, employeeForList1));

        mockMvc.perform(get("/employees")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(view().name("employees"))
                .andExpect(forwardedUrl("/WEB-INF/templates/employees.html"))
                .andExpect(model().attribute("employees", hasSize(2)))
                .andReturn();

        Mockito.verify(employeeService).getAllEmployees();
        Mockito.verifyNoMoreInteractions(employeeService);
    }



    @Test
    public void addEmployeePage() throws Exception {

        mockMvc.perform(get("/employee")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(model().attributeExists("employee"))
                .andExpect(model().attribute("isNew", true))
                .andExpect(view().name("employee"))
                .andReturn();
    }

    //edit
    @Test
    public void updateEmployeeGetPage() throws Exception {

        when(employeeService.getEmployeeById(1))
                .thenReturn(employee);

        mockMvc.perform(get("/employee/{id}", 1)
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML))

                .andExpect(status().isOk()).andDo(print())
                .andExpect(model().attribute("employee", employee))
                .andExpect(model().attribute("isNew", false))
                .andExpect(model().attributeExists("departments"))
                .andExpect(view().name("employee"))
                .andReturn();

        Mockito.verify(employeeService).getEmployeeById(1);
        Mockito.verifyNoMoreInteractions(employeeService);
    }

//
//    @Test
//    public void updateEmployeePost() throws Exception {
//
//        employeeService.update(employee);
//
//        mockMvc.perform(post("/employee/{id}", 1)
//                .accept(MediaType.TEXT_HTML)
//                .contentType(MediaType.TEXT_HTML))
//                .andExpect(status().is3xxRedirection())
//                .andDo(print())
//                .andExpect(view().name("redirect:/employees"))
//                .andReturn();
//
//        Mockito.verify(employeeService).update(refEq(employee));
//        Mockito.clearInvocations(employeeService);
//
//    }

    //delete
    @Test
    public void deleteEmployee() throws Exception {

        mockMvc.perform(get("/employee/{id}/delete", employee.getEmployeeId())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andExpect(view().name("redirect:/employees"))
                .andReturn();

        Mockito.verify(employeeService).deleteEmployeeById(employee.getEmployeeId());

    }

}

package com.epam.brest.course.controller.web_app;

import com.epam.brest.course.controllers.web_app.controllers.DepartmentController;
import com.epam.brest.course.dto.DepartmentAvgSalary;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.api.DepartmentService;
import com.epam.brest.course.service.api.EmployeeService;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:test-root-context.xml"})
public class DepartmentControllerTest {

    @Autowired
    DepartmentController departmentController;

    private DepartmentAvgSalary departmentDto1;

    private DepartmentAvgSalary departmentDto2;

    private Department department;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        //* we have to instantiate a view resolver because
        // our default resolver that gives and instance of
        // jstlview which is a wrapper class for jsp and when we
        // run tests it checks if path starts with "/"
        // and add this to uri which cause circular path but using prefix and suffix  solves this.

        //* second solution is to change view names so we dont have uri the same with view name.
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");

        this.mockMvc = MockMvcBuilders.standaloneSetup(departmentController)
                .setViewResolvers(viewResolver)
                .build();

        departmentDto1 = new DepartmentAvgSalary();
        departmentDto1.setDepartmentId(1);
        departmentDto1.setDepartmentName("JAVA");
        departmentDto1.setAvgSalary(300);

        departmentDto2 = new DepartmentAvgSalary();
        departmentDto2.setDepartmentId(2);
        departmentDto2.setDepartmentName("ED");
        departmentDto2.setAvgSalary(300);

        department = new Department();
        department.setDepartmentId(1);
        department.setDepartmentName("English");
        department.setHeadOfDepartment("NATALIA");
        department.setDescription("LEARNING");
    }

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void getDepartmentsPage() throws Exception {

        mockMvc.perform(get("/department")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML))

                .andExpect(model().attributeExists("isNew"))
                .andExpect(model().attributeExists("department"))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(view().name("department"));

    }

    @Test
    @SuppressWarnings("unchecked")
    public void getDepartmentList() throws Exception {

        when(departmentService.getDepartmentsAvgSalary())
                .thenReturn(Arrays.asList(departmentDto1, departmentDto2));

        mockMvc.perform(get("/departments")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(view().name("departments"))
                .andExpect(forwardedUrl("/WEB-INF/templates/departments.html"))
                .andExpect(model().attribute("departments", hasSize(2)))
                .andExpect(model().attribute("departments", hasItem(allOf(

                        hasProperty("departmentId", is(1)),
                        hasProperty("departmentName", is("JAVA")),
                        hasProperty("avgSalary", is(300))
                        )
                )))
                .andExpect(model().attribute("departments", hasItem(allOf(

                        hasProperty("departmentId", is(2)),
                        hasProperty("departmentName", is("ED")),
                        hasProperty("avgSalary", is(300))
                        )
                )));

        Mockito.verify(departmentService).getDepartmentsAvgSalary();
        Mockito.verifyNoMoreInteractions(departmentService);
    }




    @Test
    public void addDepartmentGetPage() throws Exception {

        mockMvc.perform(get("/department")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(model().attributeExists("department"))
                .andExpect(model().attribute("isNew", true))
                .andExpect(view().name("department"))
                .andReturn();
    }

    @Test
    public void updateDepartmentGetPage() throws Exception {

        when(departmentService.getDepartmentById(1))
                .thenReturn(department);

        mockMvc.perform(get("/department/{id}", 1)
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML))

                .andExpect(status().isOk()).andDo(print())

                .andExpect(model().attribute("department", department))
                .andExpect(model().attribute("isNew", false))
                .andExpect(view().name("department"))
                .andReturn();

        Mockito.verify(departmentService).getDepartmentById(1);
        Mockito.verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void updateDepartmentPost() throws Exception {

        departmentService.updateDepartment(department);

        mockMvc.perform(post("/department/{id}", 1)
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andExpect(view().name("redirect:/departments"))
                .andReturn();

        Mockito.verify(departmentService).updateDepartment(refEq(department));
        Mockito.clearInvocations(departmentService);
    }

    @Test
    public void deleteDepartment() throws Exception {

        mockMvc.perform(get("/department/{id}/delete", department.getDepartmentId())
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection())
                .andDo(print())
                .andExpect(view().name("redirect:/departments"))
                .andReturn();

        Mockito.verify(departmentService).deleteDepartmentById(department.getDepartmentId());

    }

}

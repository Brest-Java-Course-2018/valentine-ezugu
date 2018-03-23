package com.epam.brest.course.controller.web_app;

import com.epam.brest.course.controller.web_app.config.TestConfig;
import com.epam.brest.course.service.api.DepartmentService;
import com.epam.brest.course.service.api.EmployeeService;
import com.epam.brest.course.service.web_app.controllers.DepartmentController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DepartmentController.class, TestConfig.class})
public class DepartmentControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public final void onSetup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

     @Autowired
    private DepartmentService departmentService;

     @Autowired
     private EmployeeService employeeService;

    @Test
    public void getRedirection() throws Exception {

        mockMvc.perform(get("/")
                .accept(MediaType.TEXT_HTML)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection()).andDo(print())
                .andExpect(view().name("redirect:departments"))
                .andReturn();
    }


}

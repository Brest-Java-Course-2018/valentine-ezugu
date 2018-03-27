package com.epam.brest.course.rest;

import com.epam.brest.course.dto.DepartmentAvgSalary;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.Matchers;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
 import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rest.spring.test.xml")
public class DepartmentRestControllerMockTest {

    private static final int ID = 1;
    private static final String NAME_3 = "name3";
    private static final String DESCRIPTION = "description";
    private static final String HEAD = "Head";
    private static final int ID_2 = 2;
    private static final String NAME_1 = "name1";
    private static final String NAME_2 = "name2";

    @Autowired
    private DepartmentRestController departmentRestController;

    private DepartmentAvgSalary departmentDto1;
    private DepartmentAvgSalary departmentDto2;
    private Department department;
    private Department departmentBuilder;

    private MockMvc mockMvc;

    @Autowired
    private DepartmentService departmentService;

    @Before
    public void setUp() {
        departmentDto1 = new DepartmentAvgSalary();
        departmentDto1.setDepartmentId(ID);
        departmentDto1.setDepartmentName(NAME_1);

        departmentDto2 = new DepartmentAvgSalary();
        departmentDto2.setDepartmentId(ID_2);
        departmentDto2.setDepartmentName(NAME_2);


        department = new Department();
        department.setDepartmentId(ID);
        department.setDepartmentName(NAME_3);
        department.setDescription(DESCRIPTION);
        department.setHeadOfDepartment(HEAD);

        departmentBuilder = new Department();
        departmentBuilder.setDepartmentName(NAME_3);
        departmentBuilder.setDescription(DESCRIPTION);
        departmentBuilder.setHeadOfDepartment(HEAD);

        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();


    }

    @After
    public void tearDown() {
        reset(departmentService);
    }

    @Test
    public void getDepartments() throws Exception {
        when(departmentService.getDepartmentsAvgSalary())
                .thenReturn(Arrays.asList(departmentDto1, departmentDto2));


        mockMvc.perform(get("/departments").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].departmentId", Matchers.is(ID)))
                .andExpect(jsonPath("$[0].departmentName", Matchers.is(NAME_1)))
                .andExpect(jsonPath("$[1].departmentId", Matchers.is(ID_2)))
                .andExpect(jsonPath("$[1].departmentName", Matchers.is(NAME_2)));

        Mockito.verify(departmentService).getDepartmentsAvgSalary();
    }

    @Test
    public void getDepartmentById() throws Exception {

       when(departmentService.getDepartmentById(ID)).thenReturn(department);

    mockMvc.perform(get("/departments/{id}", 1).accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("departmentId", Matchers.is(ID)))
        .andExpect(jsonPath("departmentName", Matchers.is(NAME_3)))
        .andExpect(jsonPath("description", Matchers.is(DESCRIPTION)))
        .andExpect(jsonPath("headOfDepartment", Matchers.is(HEAD)));

        Mockito.verify(departmentService).getDepartmentById(ID);
    }


    @Test //TODO test passes only with Mockito refEq method , or solution - implement equals method.
    public void addDepartment() throws  Exception {

         when(departmentService.saveDepartment(new Department("valiko","desc","heado")))
                 .thenReturn(department);

        mockMvc.perform(post("/departments").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"departmentId\":\"1\"," +
                        "\"departmentName\":\"name3\"," +
                        "\"description\":\"description\"," +
                        "\"headOfDepartment\":\"Head\"}")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print()).andExpect(status().isCreated());

        Mockito.verify(departmentService).saveDepartment(refEq(department));

        Mockito.verifyNoMoreInteractions(departmentService);
    }

     @Test
     public void deleteDepartmentById() throws Exception {

         doNothing().when(departmentService).deleteDepartmentById(ID);

         mockMvc.perform(delete("/departments/{id}", ID).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                  .andExpect(status().isFound());
         Mockito.verify(departmentService).deleteDepartmentById(ID);
    }


    @Test
    public void updateDepartment() throws Exception {

        departmentService.updateDepartment(department);

        mockMvc.perform(
                put("/departments")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"departmentId\":1," +
                                "\"departmentName\":\"Entertainment\"," +
                                "\"description\":\"Dancing group\"," +
                                "\"headOfDepartment\":\"Maxim\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        Mockito.verify(departmentService).updateDepartment(department);
    }


    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

}
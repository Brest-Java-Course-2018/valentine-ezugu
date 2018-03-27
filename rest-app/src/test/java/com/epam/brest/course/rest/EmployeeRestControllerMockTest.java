package com.epam.brest.course.rest;


import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rest.spring.test.xml")
public class EmployeeRestControllerMockTest {


    private static final int ID = 1;
    private static final int ID_2 = 1;

    private static final String EMPLOYEE_NAME = "val";
    private static final String EMPLOYEE_NAME_2 = "jane";

    private static final int DEPARTMENT_ID = 2;
    private static final int DEPARTMENT_ID_FOR_EMPLOYEE_2 = 2;

    private static final int SALARY = 400;
    private static final String VALIK_YAHOO_COM = "valik@yahoo.com";
    private static final String VALIK_YAHOO_COM_2 = "jane@yahoo.com";
   
    @Autowired
    private EmployeeRestController employeeRestController;

    private MockMvc mockMvc;
    
    private Employee employee_One;

    private Employee employee_Two;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Before
    public void setUp() {
  
        employee_One = new Employee();
        employee_One.setEmployeeId(ID);
        employee_One.setEmployeeName(EMPLOYEE_NAME);
        employee_One.setDepartmentId(DEPARTMENT_ID);
        employee_One.setSalary(SALARY);
        employee_One.setEmail(VALIK_YAHOO_COM);


        employee_Two = new Employee();
        employee_Two.setEmployeeId(ID_2);
        employee_Two.setEmployeeName(EMPLOYEE_NAME_2);
        employee_Two.setDepartmentId(DEPARTMENT_ID_FOR_EMPLOYEE_2);
        employee_Two.setSalary(SALARY);
        employee_Two.setEmail(VALIK_YAHOO_COM_2);


        mockMvc = MockMvcBuilders.standaloneSetup(employeeRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();

    }


    @Test
    public void getAllEmployees() throws Exception {
        
        when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(employee_One,employee_Two));

        mockMvc.perform(get("/employees").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))

                .andExpect(jsonPath("$[0].employeeId", Matchers.is(ID)))
                .andExpect(jsonPath("$[0].employeeName", Matchers.is(EMPLOYEE_NAME)))
                .andExpect(jsonPath("$[0].departmentId", Matchers.is(DEPARTMENT_ID)))
                .andExpect(jsonPath("$[0].salary", Matchers.is(SALARY)))


                .andExpect(jsonPath("$[1].employeeId", Matchers.is(ID_2)))
                .andExpect(jsonPath("$[1].employeeName", Matchers.is(EMPLOYEE_NAME_2)))
                .andExpect(jsonPath("$[1].departmentId", Matchers.is(DEPARTMENT_ID_FOR_EMPLOYEE_2)))
                .andExpect(jsonPath("$[1].salary", Matchers.is(SALARY)));

          Mockito.verify(employeeService).getAllEmployees();
    }


    @Test
    public void getEmployeeById() throws Exception {

        when(employeeService.getEmployeeById(ID)).thenReturn(employee_One);

        mockMvc.perform(get("/employees/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("employeeId", Matchers.is(ID)))
                .andExpect(jsonPath("employeeName", Matchers.is(EMPLOYEE_NAME)))
                .andExpect(jsonPath("departmentId", Matchers.is(DEPARTMENT_ID )))
                .andExpect(jsonPath("salary", Matchers.is(SALARY)));

        Mockito.verify(employeeService).getEmployeeById(ID);
    }


    @Test
    public void addEmployee() throws Exception {
       when(employeeService.saveEmployee(new Employee(EMPLOYEE_NAME,SALARY,1)))
               .thenReturn(employee_Two);

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"employeeId\":4," +
                        "\"employeeName\":\"vinto\"," +
                        "\"departmentId\":\"1\"," +
                        "\"salary\":\"400\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }




    @Test
    public void deleteEmployeeById() throws Exception {

        doNothing().when(employeeService).deleteEmployeeById(ID);

        mockMvc.perform(delete("/employees/{id}", ID).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isFound());
        Mockito.verify(employeeService).deleteEmployeeById(ID);
    }


    @Test
    public void updateEmployee() throws Exception {

        employeeService.update(employee_One);

        mockMvc.perform(
                put("/employees")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"employeeId\":1," +
                                "\"employeeName\":\"James\"," +
                                "\"departmentId\":\"2\"," +
                                "\"salary\":\"500\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());

        Mockito.verify(employeeService).update(employee_One);
    }
}

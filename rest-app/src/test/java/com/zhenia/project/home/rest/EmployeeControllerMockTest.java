package com.zhenia.project.home.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhenia.project.home.domain.Employee;
import com.zhenia.project.home.dto.EmployeeDto;
import com.zhenia.project.home.service.EmployeeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by yauheniya on 5.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-rest-mock.xml"})
public class EmployeeControllerMockTest {

    @Resource
    private EmployeeRestController employeeController;

    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(employeeController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(employeeService);
        reset(employeeService);
    }

    @Test
    public void addEmployeeTest() throws Exception {
        expect(employeeService.addEmployee(anyObject(Employee.class))).andReturn(3);
        replay(employeeService);

        String employee = new ObjectMapper().writeValueAsString(new Employee("Olga", "Ivanova",1, new Date(2015-12-03)));

        mockMvc.perform(
                post("/employee/insert")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employee)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("3"));
    }

    @Test
    public void getEmployeesTest() throws Exception {
        expect(employeeService.getAllEmployees()).andReturn(Arrays.<Employee>asList(new Employee("Olga", "Ivanova",1, new Date(2015-12-03))));
        replay(employeeService);

        mockMvc.perform(
                get("/employees")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        employeeService.deleteEmployee(anyObject(Integer.class));
        expectLastCall();
        replay(employeeService);

        mockMvc.perform(
                delete("/delete/employee/2")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(""));
    }


    @Test
    public void getEmployeeByIdTest() throws Exception {
        expect(employeeService.getEmployeeById(anyObject(Integer.class))).andReturn(new Employee(1,"Elena","Chymanova", 1, new Date(2015-10-25)));
        replay(employeeService);
        mockMvc.perform(
                get("/employee/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isFound());
    }


    @Test
    public void getEmployeeDto() throws Exception {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmployees(Arrays.asList(new Employee("Olga", "Ivanova",1, new Date(2015-12-03)),
                new Employee("Oleg", "Ivanov",1, new Date(2015-12-03))));
        dto.setCount(2);
        expect(employeeService.getEmployeeDto()).andReturn(dto);
        replay(employeeService);

        mockMvc.perform(
                get("/employeedto")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

}
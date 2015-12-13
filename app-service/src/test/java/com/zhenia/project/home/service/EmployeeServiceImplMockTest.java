package com.zhenia.project.home.service;

import com.zhenia.project.home.dao.EmployeeDao;
import com.zhenia.project.home.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Created by yauheniya on 21.10.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test-spring-service-mock.xml" })
public class EmployeeServiceImplMockTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDao mockEmployeeDao;

    private static final Employee employee = new Employee("Olga", "Ivanova",1, new Date(2015-12-03));

    @After
    public void clean() {
        verify(mockEmployeeDao);
        reset(mockEmployeeDao);
    }

    @Test
    public void testGetAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList();
        expect(mockEmployeeDao.getAllEmployees()).andReturn(employees);
        replay(mockEmployeeDao);
        int id = employeeService.getAllEmployees().size();
        Assert.assertTrue(id == employees.size());
    }

    @Test
    public void testInsert() throws SQLException {
        expect(mockEmployeeDao.addEmployee(employee)).andReturn(5);
        replay(mockEmployeeDao);
        int id = employeeService.addEmployee(employee);
        Assert.assertTrue(id == 5);
    }

    @Test
    public void testGetById() throws SQLException {
        expect(mockEmployeeDao.getEmployeeById(1)).andReturn(employee);
        replay(mockEmployeeDao);
        Employee result = employeeService.getEmployeeById(1);
        Assert.assertTrue(result != null);

    }

    @Test
    public void testGetEmployeeByVacation() throws SQLException {
       String str = "2015-03-25";
       String str2="2018-11-20";
       Date dt = null;
       Date dt2 = null;
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       try {
           dt = formatter.parse(str);
           dt2=formatter.parse(str2);
       } catch (Exception e) {
       }
        List<Employee> listemployee = new ArrayList();
        expect(mockEmployeeDao.getEmployeeByVacation(dt, dt2)).andReturn(listemployee);
        replay(mockEmployeeDao);
        List<Employee> result = employeeService.getEmployeeByVacation(dt, dt2);
        Assert.assertTrue(result == listemployee);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetEmployeeByVacationException() throws SQLException {
        String str = "2020-03-25";
        String str2="2013-11-20";
        Date dt = null;
        Date dt2 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = formatter.parse(str);
            dt2=formatter.parse(str2);
        } catch (Exception e) {
        }
        expect(mockEmployeeDao.getEmployeeByVacation(dt, dt2)).andThrow(new UnsupportedOperationException());
        replay(mockEmployeeDao);
        employeeService.getEmployeeByVacation(dt, dt2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetEmployeeByWrongVacation() throws SQLException {
        String str = "20200325";
        String str2="hfjfjj";
        Date dt = null;
        Date dt2 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = formatter.parse(str);
            dt2=formatter.parse(str2);
        } catch (Exception e) {
        }
        expect(mockEmployeeDao.getEmployeeByVacation(dt, dt2)).andThrow(new UnsupportedOperationException());
        replay(mockEmployeeDao);
        employeeService.getEmployeeByVacation(dt, dt2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetEmployeeByNullVacation() throws SQLException {
        String str = null;
        String str2 = null;
        Date dt = null;
        Date dt2 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = formatter.parse(str);
            dt2=formatter.parse(str2);
        } catch (Exception e) {
        }
        expect(mockEmployeeDao.getEmployeeByVacation(dt, dt2)).andThrow(new UnsupportedOperationException());
        replay(mockEmployeeDao);
        employeeService.getEmployeeByVacation(dt, dt2);
    }

    @Test(expected = AssertionError.class)
    public void testGetWrongById() throws SQLException {
        expect(mockEmployeeDao.getEmployeeById(1000)).andReturn(employee);
        replay(mockEmployeeDao);
        Employee result = employeeService.getEmployeeById(1000);
        Assert.assertTrue(result == null);

    }
}

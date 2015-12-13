package com.zhenia.project.home.dao;

import com.zhenia.project.home.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * Created by yauheniya on 27.10.15.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-test-dao.xml"})
@Transactional()
public class EmployeeDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private EmployeeDao employeeDao;

     @Test
    public void testGetAllEmployees() throws SQLException {
        LOGGER.debug("test: getAllEmployees()");
        List<Employee> employees = employeeDao.getAllEmployees();
        assertTrue(employees.size() > 0);
    }

    @Test
    public void testGetEmployeeById() throws SQLException {
        LOGGER.debug("test: getEmployeeById()");
        int Id = 1;
        Employee employee = employeeDao.getEmployeeById(Id);
        assertNotNull(employee);
        assertTrue(employee.getEmplId() == Id);
    }

    @Test
    public void testGetEmployeeByVacation() throws SQLException {
        LOGGER.debug("test: getEmployeeByVacation()");
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
        List<Employee>  employee = employeeDao.getEmployeeByVacation(dt,dt2);
        assertNotNull(employee);
        assertTrue(employee.size() > 0);
    }

    @Test
    public void testAddEmployee() throws SQLException {
        LOGGER.debug("test: addEmployee()");
        Employee test = new Employee();
        test.setName("Yauheniya");
        test.setSurname("Yarmaliuk");
        test.setIdVoucher(1);
        String str = "2015-12-25";
        Date dt = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = formatter.parse(str);
        } catch (Exception e) {
        }
        test.setVacation(dt);
        List<Employee> list = employeeDao.getAllEmployees();
        Integer var1 = list.size();
        employeeDao.addEmployee(test);
        List<Employee> list2 = employeeDao.getAllEmployees();
        Integer var2 = list2.size();
        assertTrue(var2 > var1);
    }

    @Test
    public void testUpdateEmployee() throws SQLException {
        LOGGER.debug("test: updateEmployee()");
        Employee employee = employeeDao.getEmployeeById(1);
        employee.setSurname("Bezverbnaya");
        employee.setIdVoucher(1);
        String str = "2015-12-25";
        Date dt = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = formatter.parse(str);
        } catch (Exception e) {
        }
        employee.setVacation(dt);
        employeeDao.updateEmployee(employee);
        assertTrue(employee.getEmplId()== 1);
        assertTrue(employee.getSurname().equals("Bezverbnaya"));
        assertTrue(employee.getIdVoucher().equals(1));

    }

    @Test
    public void testDeleteEmployee() throws SQLException {
        LOGGER.debug("test :deleteEmployee()");
        List<Employee> employees = employeeDao.getAllEmployees();
        assertTrue(employees.size() > 0);
        int sizeBefore = employees.size();
        employeeDao.deleteEmployee(employees.get(0).getEmplId());
        assertTrue((sizeBefore - 1) == employeeDao.getAllEmployees().size());
    }

    @Test
    public void testTotalEmployeeCount() throws SQLException {
        LOGGER.debug("test: totalEmployeeCount()");
        Integer length = employeeDao.getAllEmployees().size();
        Integer emplCount = employeeDao.getTotalEmployeeCount();
        assertEquals(length,emplCount);
    }

}
package com.zhenia.project.home.service;

import com.zhenia.project.home.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yauheniya on 4.11.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class EmployeeServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private EmployeeService employeeService;

    private static final Employee addemployee = new Employee("Oksana", "Sidoruk",2, new Date(2015-12-03));
    private static final Employee newEmployee = new Employee(4,"Oksana", "Sidoruk",2, new Date(2015-12-03));

    @Test
    public void testGetAllEmployees() throws Exception {
        LOGGER.debug("test: getAllEmployees()");
        Assert.assertTrue(employeeService.getAllEmployees().size() > 0);
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        LOGGER.debug("test: UpdateEmployee()");
        Employee employee = employeeService.getEmployeeById(1);
        employee.setName("Ksusha");
        employee.setSurname("Sidoruk");
        employee.setIdVoucher(1);
        employee.setVacation(new Date(2015-06-05));
        employeeService.updateEmployee(employee);
        Assert.assertTrue(employee.getEmplId().equals(1));
        Assert.assertTrue(employee.getSurname().equals("Sidoruk"));
    }

    @Test
    public void testAddEmployee() throws Exception {
        LOGGER.debug("test: AddEmployee()");
        List<Employee> list = employeeService.getAllEmployees();
        Integer var1 = list.size();
        employeeService.addEmployee(addemployee);
        List<Employee> list2 = employeeService.getAllEmployees();
        Integer var2 = list2.size();
        Assert.assertTrue( var2>var1);
    }

    @Test
    public void testGetDtoEmployees() throws Exception {
        LOGGER.debug("test: getDtoEmployees()");
        Assert.assertTrue(employeeService.getEmployeeDto().getCount() > 0);
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        LOGGER.debug("test: DeleteEmployee()");
        List<Employee> list = employeeService.getAllEmployees();
        Integer var1 = list.size();
        Employee employee = employeeService.getEmployeeById(1);
        employeeService.deleteEmployee(1);
        List<Employee> list2 = employeeService.getAllEmployees();
        Integer var2 = list2.size();
        Assert.assertTrue( var2<var1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmployeeWithNotNullId() throws Exception {
        LOGGER.debug("test: addEmployeeWithNotNullId()");
        employeeService.addEmployee(newEmployee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmployeeWithNullAll() throws Exception {
        LOGGER.debug("test: AddEmployeeWithNullAll()");
        Employee employee = new Employee();
        employeeService.addEmployee(employee);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testUpdateEmployeeWithNullAll() throws Exception {
        LOGGER.debug("test: UpdateEmployeeWithNullAll()");
        Employee employee = new Employee();
        employeeService.updateEmployee(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateEmployeeWithNullId() throws Exception {
        LOGGER.debug("test: UpdateEmployeeWithNullId()");
        Employee employee = new Employee();
        employee.setSurname("Sidoruk");
        employee.setIdVoucher(1);
        employee.setVacation(new Date(2015-06-05));
        employeeService.updateEmployee(employee);
    }


   @Test(expected = IllegalArgumentException.class)
    public void testAddEmployeeWithNullVacation() throws Exception {
        LOGGER.debug("test: AddEmployeeWithNullVacation()");
        Employee employee = new Employee();
        employee.setName("Oksana");
        employee.setSurname("Sidoruk");
        employee.setIdVoucher(1);
        employeeService.addEmployee(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmployeeWithNullName() throws Exception {
        LOGGER.debug("test: AddEmployeeWithNullName()");
        Employee employee = new Employee();
        employee.setSurname("Sidoruk");
        employee.setIdVoucher(1);
        String str = "2015-12-25";
        Date dt = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = formatter.parse(str);
        } catch (Exception e) {
        }
        employee.setVacation(dt);
        employeeService.addEmployee(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmployeeWithNullSurname() throws Exception {
        LOGGER.debug("test: AddEmployeeWithNullSurname()");
        Employee employee = new Employee();
        employee.setName("Oksana");
        employee.setIdVoucher(1);
        String str = "2015-12-25";
        Date dt = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = formatter.parse(str);
        } catch (Exception e) {
        }
        employee.setVacation(dt);
        employeeService.addEmployee(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmployeeWrongDateFormat() throws Exception {
        LOGGER.debug("test: AddEmployeeWrongDateFormat()");
        Employee employee = new Employee();
        employee.setName("Oksana");
        employee.setSurname("Sidoruk");
        employee.setIdVoucher(1);
        String str = "jhgjhgj";
        Date dt = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = formatter.parse(str);
        } catch (Exception e) {
        }
        employee.setVacation(dt);
        employeeService.addEmployee(employee);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmployeeByIdNullId() throws Exception {
        LOGGER.debug("test: EmployeeByIdNullId()");
        Integer empl = null;
        employeeService.getEmployeeById(empl);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testEmployeeByIdEmptyId() throws Exception {
        LOGGER.debug("test: EmployeeByIdEmptyId()");
        Integer empl = 10000;
        employeeService.getEmployeeById(empl);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmployeeDeleteEmptyId() throws Exception {
        LOGGER.debug("test: EmployeeDeleteEmptyId()");
        Integer empl = null;
        employeeService.deleteEmployee(empl);
    }
}
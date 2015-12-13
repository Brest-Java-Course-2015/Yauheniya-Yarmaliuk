package com.zhenia.project.home.domain;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by yauheniya on 4.11.15.
 */


public class EmployeeTest {

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        employee = new Employee();
    }

    @Test
    public void testGetEmployeeId() throws Exception {
        employee.setEmplId(2);
        assertEquals(2, employee.getEmplId(), 0);
    }

    @Test
    public void testGetName() throws Exception {
        employee.setName("Olga");
        assertEquals("Olga", employee.getName());
    }

    @Test
    public void testGetSurname() throws Exception {
        employee.setSurname("Petrova");
        assertEquals("Petrova", employee.getSurname());
    }

    @Test
    public void testGetDateVacation() throws Exception {
        String str = "2015-10-06";
        Date dt = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = formatter.parse(str);
        } catch (Exception e) {
        }
        employee.setVacation(dt);
        assertEquals(dt, employee.getVacation());
    }

    @Test
    public void testEmployeeNullId() throws Exception {
        assertEquals(employee.getEmplId(), null);
    }

    @Test
    public void testFlaytName() throws Exception {
        employee.setName("Olga");
        assertNotEquals(employee.getName(), "OLGA");
    }

    @Test
    public void testGetIdVoucher() throws Exception {
        employee.setIdVoucher(1);
        assertEquals(1, employee.getIdVoucher(), 0);
    }
}
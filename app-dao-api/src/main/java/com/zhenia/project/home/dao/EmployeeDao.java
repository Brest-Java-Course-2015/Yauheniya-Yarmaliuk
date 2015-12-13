package com.zhenia.project.home.dao;

import com.zhenia.project.home.domain.Employee;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by yauheniya on 4.11.15.
 */
public interface EmployeeDao {

    public List<Employee> getAllEmployees() throws SQLException;

    public Employee getEmployeeById(Integer emplId) throws SQLException;

    public List<Employee>  getEmployeeByVacation (Date vacation, Date vacation2) throws SQLException;

    public Integer getTotalEmployeeCount() throws SQLException;

    public Integer addEmployee (Employee employee) throws SQLException;

    public void updateEmployee (Employee employeet) throws SQLException;

    public void deleteEmployee (Integer emplId) throws SQLException;

}

package com.zhenia.project.home.service;

import com.zhenia.project.home.dao.EmployeeDao;
import com.zhenia.project.home.domain.Employee;
import com.zhenia.project.home.dto.EmployeeDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
/**
 * Created by yauheniya on 4.11.15.
 */

@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LogManager.getLogger();

    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        LOGGER.debug("getAllEmployees()");
        return employeeDao.getAllEmployees();
    }

    @Override
    public Integer addEmployee(Employee employee) throws SQLException {
        Assert.notNull(employee, "Employee should not be null.");
        LOGGER.debug("addEmployee(): employee employee = {} ", employee.getEmplId());
        Assert.isNull(employee.getEmplId(), "Employee Id should be null.");
        Assert.hasText(employee.getName(), "Employee name should not be null.");
        Assert.hasText(employee.getSurname(), "Employee surname should not be null.");
        Assert.notNull(employee.getIdVoucher(), "Employee idVoucher should not be null.");
        Assert.notNull(employee.getVacation(),"Employee vacation should not be null");

        return employeeDao.addEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(Integer emplId) throws SQLException {
        LOGGER.debug("getEmployeeById(): employee id = {} ", emplId);
        Assert.notNull(emplId, "Employee Id should not be null.");
        Assert.isTrue(emplId > 0);
        return employeeDao.getEmployeeById(emplId);
    }

    @Override
    public List<Employee>  getEmployeeByVacation (Date vacation, Date vacation2) throws SQLException {
        LOGGER.debug("getEmployeeByVacation: employee vacation = {} ", vacation);
           return employeeDao.getEmployeeByVacation(vacation, vacation2);
    }

    @Override
    public void updateEmployee (Employee employee) throws SQLException {
        Assert.notNull(employee, "Employee should not be null.");
        LOGGER.debug("updateEmployee(): employee name = {} ", employee.getName(),employee.getSurname(),
                employee.getIdVoucher(), employee.getVacation());
        Assert.notNull(employee.getEmplId(), "Employee Id should not be null.");
        Assert.hasText(employee.getName(), "Employee surname should not be null.");
        Assert.hasText(employee.getSurname(), "Employee surname should not be null.");
        Assert.notNull(employee.getIdVoucher(), "Employee idVoucher should not be null.");
        Assert.notNull(employee.getVacation(),"Employee vacation should not be null");
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee (Integer emplId) throws SQLException {
        LOGGER.debug("deleteEmployee():employee id = {} ", emplId);
        Assert.notNull(emplId, "Employee Id should not be null.");
        Assert.isTrue(emplId > 0);
        employeeDao.deleteEmployee(emplId);
    }

    @Override
    public EmployeeDto getEmployeeDto() throws SQLException {
        EmployeeDto employeeDto = new  EmployeeDto();
        employeeDto.setCount(employeeDao.getTotalEmployeeCount());
        if (employeeDto.getCount() > 0) {
            employeeDto.setEmployees(employeeDao.getAllEmployees());
        } else {
            employeeDto.setEmployees(Collections.<Employee>emptyList());
        }
        return employeeDto;
    }

}

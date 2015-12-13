package com.zhenia.project.home.rest;

import com.zhenia.project.home.domain.Employee;
import com.zhenia.project.home.dto.EmployeeDto;
import com.zhenia.project.home.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * Created by yauheniya on 5.11.15.
 */

@RestController
public class EmployeeRestController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public  List<Employee> getEmployees() throws SQLException {
        LOGGER.debug("getEmployees()");
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/employee/insert", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addEmployeer(@RequestBody Employee employee) throws SQLException {
        LOGGER.debug("addEmployee: employee = {}", employee);
        return employeeService.addEmployee(employee);
    }

    @RequestMapping(value = "/update/employee", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateEmployee(@RequestBody Employee employee) throws SQLException {
        Employee dbEmployee = employeeService.getEmployeeById(employee.getEmplId());
        dbEmployee.setName(employee.getName());
        dbEmployee.setSurname(employee.getSurname());
        dbEmployee.setIdVoucher(employee.getIdVoucher());
        dbEmployee.setVacation(employee.getVacation());
        employeeService.updateEmployee(dbEmployee);
    }

    @RequestMapping(value = "/employee/{emplId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public Employee getEmployee(@PathVariable(value = "emplId") Integer emplId) throws SQLException {
        LOGGER.debug("getEmployee: emplId = {}", emplId);
        return employeeService.getEmployeeById(emplId);
    }

    @RequestMapping(value = "/delete/employee/{emplId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEmployee(@PathVariable(value = "emplId") Integer emplId) throws SQLException {
        employeeService.deleteEmployee(emplId);

    }

    @RequestMapping(value = "/employeedto", method = RequestMethod.GET)
    public EmployeeDto getEmployeeDto() throws SQLException {
        LOGGER.debug("getEmployeeDto()");
        return employeeService.getEmployeeDto();
    }

    public static Date getDOB(String vacation) {

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(vacation);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/searchVacation/{date1}/{date2}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public  List<Employee> getEmployee(@PathVariable String date1, @PathVariable String date2) throws SQLException {
        LOGGER.debug("getEmployee: vacation = {}", date1);
        Date vacation = getDOB(date1);
        Date vacation2 = getDOB(date2);
        return employeeService.getEmployeeByVacation(vacation, vacation2);
    }
}

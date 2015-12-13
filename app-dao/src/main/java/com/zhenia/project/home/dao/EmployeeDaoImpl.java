package com.zhenia.project.home.dao;

import com.zhenia.project.home.domain.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhenia.project.home.domain.Employee.EmployeeFields.*;
/**
 * Created by yauheniya on 4.11.15.
 */

public class EmployeeDaoImpl implements EmployeeDao {

    private static final Logger LOGGER = LogManager.getLogger(EmployeeDaoImpl.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Value("${employee.select}")
    private String employeeSelect;

    @Value("${employee.selectById}")
    private String employeeSelectById;

    @Value("${employee.selectByVacation}")
    private String employeeSelectByVacation;

    @Value("${employee.count}")
    private String employeeCount;

    @Value("${employee.insert}")
    private String addEmployee;

    @Value("${employee.delete}")
    private String deleteEmployee;

    @Value("${employee.update}")
    private String updateEmployee;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        LOGGER.info("getAllEmployees()");
        return namedParameterJdbcTemplate.query(employeeSelect, new EmployeeRowMapper());
    }

    @Override
    public Employee getEmployeeById(Integer emplId) throws SQLException {
        LOGGER.info("getEmployeeById({})", emplId);
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("emplId", emplId);
        return namedParameterJdbcTemplate.queryForObject(employeeSelectById, namedParameters,  new EmployeeRowMapper());
    }

    @Override
    public List<Employee>  getEmployeeByVacation (Date vacation,Date vacation2 ) throws SQLException {
        LOGGER.info("getUserByVacation({})", vacation, vacation2);
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("vacation", vacation);
        namedParameters.put("vacation2", vacation2);
        return namedParameterJdbcTemplate.query(employeeSelectByVacation, namedParameters, new EmployeeRowMapper());
    }

    @Override
    public Integer getTotalEmployeeCount() throws SQLException {
        LOGGER.debug("getTotalEmployeeCount()");
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        return namedParameterJdbcTemplate.queryForObject(employeeCount, namedParameters, Integer.class);
    }

    @Override
    public Integer addEmployee(Employee employee) throws SQLException {
        LOGGER.info("addEmployee(employee): {}", employee.getName(),employee.getSurname(),
                employee.getIdVoucher(), dateFormat.format(employee.getVacation()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addEmployee, getParametersMap(employee), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        LOGGER.info("updateEmployee (employee): {}", employee.getName());
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("emplId", employee.getEmplId());
        namedParameters.put("surname", employee.getSurname());
        namedParameters.put("idVoucher", employee.getIdVoucher());
        namedParameters.put("vacation", employee.getVacation());
        namedParameterJdbcTemplate.update(updateEmployee,namedParameters);
    }

    @Override
    public void deleteEmployee(Integer emplId) throws SQLException {
        LOGGER.info("deleteEmployee(): {}", emplId);
        Map<String, Integer> namedParameters = new HashMap<String, Integer>();
        namedParameters.put("emplId", emplId);
        namedParameterJdbcTemplate.update(deleteEmployee, namedParameters );
    }

    private MapSqlParameterSource getParametersMap(Employee employee) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(EMPLOYEE_ID.getValue(), employee.getEmplId());
        parameterSource.addValue(NAME.getValue(), employee.getName());
        parameterSource.addValue(SURNAME.getValue(), employee.getSurname());
        parameterSource.addValue(IdVOUCHER.getValue(), employee.getIdVoucher());
        parameterSource.addValue(VACATION.getValue(), employee.getVacation());
        return parameterSource;
    }

    private class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee = new Employee(resultSet.getInt(EMPLOYEE_ID.getValue()),
                resultSet.getString(NAME.getValue()),
                resultSet.getString(SURNAME.getValue()),
                resultSet.getInt(IdVOUCHER.getValue()),
                resultSet.getDate(VACATION.getValue()));
            return employee;
        }
    }
}


package com.zhenia.project.home.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhenia.project.home.domain.Employee;

import java.util.List;

/**
 * Created by yauheniya on 4.11.15.
 */
public class EmployeeDto {

    private List<Employee> employees;

    private int count;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

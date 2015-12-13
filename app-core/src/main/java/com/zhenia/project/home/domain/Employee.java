package com.zhenia.project.home.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by yauheniya on 4.11.15.
 */
public class Employee {
    private Integer emplId;
    private String name;
    private String surname;
    private Integer idVoucher;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date vacation;

    public Employee(){}

    public Employee(Integer emplId, String name, String surname, Integer idVoucher, Date vacation) {
        this.emplId = emplId;
        this.name = name;
        this.surname = surname;
        this.idVoucher = idVoucher;
        this.vacation = vacation;
    }

    public Employee(Integer emplId, String surname, Integer idVoucher, Date vacation) {
        this.emplId = emplId;
        this.surname = surname;
        this.idVoucher = idVoucher;
        this.vacation = vacation;
    }

    public Employee(String name, String surname, Integer idVoucher, Date vacation) {
        this.name = name;
        this.surname = surname;
        this.idVoucher = idVoucher;
        this.vacation = vacation;
    }

    public Integer getEmplId() {
        return emplId;
    }

    public void setEmplId(Integer emplId) {
        this.emplId = emplId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(Integer idVoucher) {
        this.idVoucher = idVoucher;
    }

    public Date getVacation() {
        return vacation;
    }

    public void setVacation(Date vacation) {
        this.vacation = vacation;
    }

    public enum EmployeeFields {

        EMPLOYEE_ID("emplId"),
        NAME("name"),
        SURNAME("surname"),
        IdVOUCHER("idVoucher"),
        VACATION("vacation");


        EmployeeFields(String value) {
            this.value = value;
        }

        private final String value;

        public String getValue() {
            return value;
        }

    }

    @Override
    public String toString() {
        return String.format("Employee: {" +
            "emplId=" + emplId +
            ", name='" + name +
            ", surname =" + surname +
            ", idVoucher=" + idVoucher +
            ", vacation=" + vacation +
        '}');
    }
}

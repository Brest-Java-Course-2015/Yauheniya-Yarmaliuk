package com.zhenia.project.home.domain;

/**
 * Created by yauheniya on 6.11.15.
 */
public class Cost {
    private String name;
    private String surname;
    private Integer yourprice;
    private Employee employee;
    private Voucher voucher;


    public Cost(String name,String surname,Integer yourprice) {
        this.name = name;
        this.surname = surname;
        this.yourprice = yourprice;
    }

    public Cost(){}

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public Integer getYourprice() {
        return yourprice;
    }

    public void setYourprice(Integer yourprice) {
        this.yourprice = yourprice;
    }

    public enum CostFields {

        NAME("name"),
        SURNAME("surname"),
        YOURPRICE("yourprice");

        CostFields(String value) {
            this.value = value;
        }

        private final String value;

        public String getValue() {
            return value;
        }

    }

    @Override
    public String toString() {
        return String.format("Cost: {" +
            " name='" + name +
            ", surname =" + surname +
            ", yourprice=" + yourprice +

        '}');
    }
}

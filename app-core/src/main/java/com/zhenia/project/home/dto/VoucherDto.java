package com.zhenia.project.home.dto;

/**
 * Created by yauheniya on 07.12.15.
 */
public class VoucherDto {

    private Integer vouchId;
    private String  country;
    private Integer price;
    private Integer discaunt;
    private Integer countPeopl;

    public VoucherDto(){};

    public VoucherDto(Integer vouchId, String country, Integer price, Integer discaunt, Integer countPeopl) {
        this.vouchId = vouchId;
        this.country = country;
        this.price = price;
        this.discaunt = discaunt;
        this.countPeopl=countPeopl;

    }

    public Integer getCountPeopl() {
        return countPeopl;
    }

    public void setCountPeopl(Integer countPeopl) {
        this.countPeopl = countPeopl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getDiscaunt() {
        return discaunt;
    }

    public void setDiscaunt(Integer discaunt) {
        this.discaunt = discaunt;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getVouchId() {
        return vouchId;
    }

    public void setVouchId(Integer vouchId) {
        this.vouchId = vouchId;
    }

    public enum VoucherDtoFields {

        Voucher_ID("vouchId"),
        COUNTRY("country"),
        PRICE("price"),
        DISCAUNT("discaunt"),
        COUNTPEOPL("countPeopl");


        VoucherDtoFields(String value) {
            this.value = value;
        }

        private final String value;

        public String getValue() {
            return value;
        }

    }

    @Override
    public String toString() {
        return String.format("Voucher: {" +
                "vouchId=" + vouchId +
                ", country='" + country + '\'' +
                ", price =" + price +
                ", discaunt=" + discaunt +
                ", countPeopl=" + countPeopl +
                '}');
    }
}





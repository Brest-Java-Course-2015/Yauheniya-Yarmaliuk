package com.zhenia.project.home.domain;

/**
 * Created by yauheniya on 4.11.15.
 */
public class Voucher {
    private Integer vouchId;
    private String  country;
    private Integer price;
    private Integer discaunt;

    public Voucher(){};

    public Voucher(Integer vouchId, String country, Integer price, Integer discaunt) {
        this.vouchId = vouchId;
        this.country = country;
        this.price = price;
        this.discaunt = discaunt;

    }

    public Voucher(Integer vouchId, Integer price, Integer discaunt) {
        this.vouchId = vouchId;
        this.price = price;
        this.discaunt = discaunt;

    }

    public Voucher( String country, Integer price, Integer discaunt) {
        this.country = country;
        this.price = price;
        this.discaunt = discaunt;
    }

    public Integer getVouchId() {
        return vouchId;
    }

    public void setVouchId(Integer vouchId) {
        this.vouchId = vouchId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscaunt() {
        return discaunt;
    }

    public void setDiscaunt(Integer discaunt) {
        this.discaunt = discaunt;
    }


    public enum VoucherFields {

        Voucher_ID("vouchId"),
        COUNTRY("country"),
        PRICE("price"),
        DISCAUNT("discaunt");


        VoucherFields(String value) {
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

        '}');
    }
}

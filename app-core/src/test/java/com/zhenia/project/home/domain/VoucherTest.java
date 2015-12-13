package com.zhenia.project.home.domain;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by yauheniya on 27.10.15.
 */

public class VoucherTest {

    private Voucher voucher;

    @Before
    public void setUp() throws Exception {
        voucher = new Voucher();
    }

    @Test
    public void testGetVouchertId() throws Exception {
        voucher.setVouchId(2);
        assertEquals(2, voucher.getVouchId(), 0);
    }


    @Test
    public void testGetCountry() throws Exception {
        voucher.setCountry("Poland");
        assertEquals("Poland", voucher.getCountry());
    }

    @Test
    public void testGetPrice() throws Exception {
        voucher.setPrice(300);
        assertEquals(300, voucher.getPrice(),0);
    }

    @Test
    public void testGetDiscaunt() throws Exception {
        voucher.setDiscaunt(30);
        assertEquals(30, voucher.getDiscaunt(),0);
    }

    @Test
    public void testVoucherNullId() throws Exception {
        assertEquals(voucher.getVouchId(), null);
    }

    @Test
    public void testFlaytCountry() throws Exception {
        voucher.setCountry("Turkey");
        assertNotEquals( voucher.getCountry(), "TURKEY");
    }

}

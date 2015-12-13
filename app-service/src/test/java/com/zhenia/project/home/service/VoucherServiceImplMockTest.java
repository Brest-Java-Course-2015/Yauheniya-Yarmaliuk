package com.zhenia.project.home.service;

import com.zhenia.project.home.dao.VoucherDao;
import com.zhenia.project.home.domain.Voucher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Created by yauheniya on 21.10.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test-spring-service-mock.xml" })
public class VoucherServiceImplMockTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private VoucherDao mockVoucherDao;

    private static final Voucher voucher = new Voucher("Germany", 180, 30);

    @After
    public void clean() {
        verify(mockVoucherDao);
        reset(mockVoucherDao);
    }

    @Test
    public void testInsert() throws SQLException {
        expect(mockVoucherDao.addVoucher(voucher)).andReturn(4);
        replay(mockVoucherDao);
        int id = voucherService.addVoucher(voucher);
        Assert.assertTrue(id==4 );
    }

    @Test
    public void testGetById() throws SQLException {
        expect(mockVoucherDao.getVoucherById(1)).andReturn(voucher);
        replay(mockVoucherDao);
        Voucher result = voucherService.getVoucherById(1);
        Assert.assertTrue(result != null);

    }

    @Test
    public void testGetAllVouchers() throws SQLException {
        List<Voucher> vouchers = new ArrayList();
        expect(mockVoucherDao.getAllVouchers()).andReturn(vouchers);
        replay(mockVoucherDao);
        int id = voucherService.getAllVouchers().size();
        Assert.assertTrue(id == vouchers.size());
    }

    @Test(expected = AssertionError.class)
    public void testGetWrongById() throws SQLException {
        expect(mockVoucherDao.getVoucherById(1000)).andReturn(voucher);
        replay(mockVoucherDao);
        Voucher result = voucherService.getVoucherById(1000);
        Assert.assertTrue(result == null);

    }

}

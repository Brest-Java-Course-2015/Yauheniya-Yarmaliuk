package com.zhenia.project.home.service;

import com.zhenia.project.home.domain.Voucher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

;

/**
 * Created by yauheniya on 4.11.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class VoucherServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private VoucherService voucherService;

    private static final Voucher voucher = new Voucher("Russia", 150, 30);
    private static final Voucher newVoucher = new Voucher(5,"Canada", 3000, 30);

    @Test
    public void testGetAllVouchers() throws Exception {
        LOGGER.debug("test: getAllVouchers()");
        Assert.assertTrue(voucherService.getAllVouchers().size() > 0);
    }

    @Test
    public void testUpdateVoucher() throws Exception {
        LOGGER.debug("test: UpdateVoucher()");
        Voucher voucher = voucherService.getVoucherById(1);
        voucher.setCountry("Poland");
        voucher.setPrice(150);
        voucher.setDiscaunt(45);
        voucherService.updateVoucher(voucher);
        Assert.assertTrue(voucher.getPrice().equals(150));
        Assert.assertTrue(voucher.getDiscaunt().equals(45));
    }

    @Test
    public void testAddVoucher() throws Exception {
        LOGGER.debug("test: AddVoucher()");
        List<Voucher> list = voucherService.getAllVouchers();
        Integer var1 = list.size();
        voucherService.addVoucher(voucher);
        List<Voucher> list2 = voucherService.getAllVouchers();
        Integer var2 = list2.size();
        Assert.assertTrue( var2>var1);
    }

    @Test
    public void testDeleteVoucher() throws Exception {
        LOGGER.debug("test: DeleteVoucher()");
        List<Voucher> list = voucherService.getAllVouchers();
        Integer var1 = list.size();
        voucherService.deleteVoucher(5);
        List<Voucher> list2 = voucherService.getAllVouchers();
        Integer var2 = list2.size();
        Assert.assertTrue( var2<var1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testDeleteUseVoucher() throws Exception {
        LOGGER.debug("test: DeleteUseVoucher()");
        voucherService.deleteVoucher(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertVoucher() throws Exception {
        LOGGER.debug("test: addNullVoucher()");
        voucherService.addVoucher(newVoucher);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVoucherWithNotNullId() throws Exception {
        LOGGER.debug("test: addVoucherWithNotNullId()");
        Voucher voucher = new Voucher();
        voucher.setVouchId(1);
        voucherService.addVoucher(voucher);
    }

   @Test(expected = IllegalArgumentException.class)
    public void testAddVoucherWithNullCountry() throws Exception {
        LOGGER.debug("test: AddVoucherWithNullCountry()");
        Voucher voucher = new Voucher();
        voucher.setPrice(50);
        voucher.setDiscaunt(20);
        voucherService.addVoucher(voucher);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddVoucherWithNullPrice() throws Exception {
        LOGGER.debug("test: addVoucherWithNullPrice()");
        Voucher voucher = new Voucher();
        voucher.setCountry("Poland");
        voucher.setDiscaunt(20);
        voucherService.addVoucher(voucher);
    }
}

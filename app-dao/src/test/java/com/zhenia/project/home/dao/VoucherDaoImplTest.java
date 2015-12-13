package com.zhenia.project.home.dao;

import com.zhenia.project.home.domain.Voucher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by yauheniya on 27.10.15.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-test-dao.xml"})
@Transactional()
public class VoucherDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final Voucher voucher = new Voucher("FRANCE",400, 30);

    @Autowired
    private VoucherDao voucherDao;

    @Test
    public void testGetAllVouchers() throws SQLException {
        LOGGER.debug("test: getAllVouchers()");
        List<Voucher> vouchers = voucherDao.getAllVouchers();
        assertTrue(vouchers.size() > 0);
    }

   @Test
    public void testAddVoucher() throws SQLException {
        LOGGER.debug("test: AddVouchert ()");
        Integer Id = voucherDao.addVoucher(voucher);
        assertNotNull(Id);
        Voucher newVoucher = voucherDao.getVoucherById(Id);
        assertNotNull(newVoucher);
        assertTrue(voucher.getPrice().equals(newVoucher.getPrice()));
   }

    @Test
    public void testUpdateVoucher() throws SQLException {
        LOGGER.debug("test: updateVoucher ()");
        Voucher voucher = voucherDao.getVoucherById(1);
        voucher.setDiscaunt(70);
        voucherDao.updateVoucher(voucher);
        assertTrue(voucher.getVouchId() == 1);
        assertTrue(voucher.getDiscaunt().equals(70));
    }

    @Test
    public void testDeleteVoucher() throws SQLException {
        LOGGER.debug("test :deleteVoucher()");
        List<Voucher> vouchers = voucherDao.getAllVouchers();
        assertTrue(vouchers.size() > 0);
        int sizeBefore = vouchers.size();
        voucherDao.deleteVoucher(5);
        assertTrue((sizeBefore - 1) == voucherDao.getAllVouchers().size());
    }

}

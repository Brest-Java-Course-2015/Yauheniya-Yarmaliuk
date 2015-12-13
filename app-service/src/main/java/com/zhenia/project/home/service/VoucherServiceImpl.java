package com.zhenia.project.home.service;

import com.zhenia.project.home.dao.VoucherDao;
import com.zhenia.project.home.domain.Voucher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yauheniya on 14.10.15.
 */
@Transactional
public class VoucherServiceImpl implements VoucherService {
    private static final Logger LOGGER = LogManager.getLogger();

    private VoucherDao voucherDao;

    public void setVoucherDao(VoucherDao voucherDao) {
        this.voucherDao = voucherDao;
    }

    @Override
    public List<Voucher> getAllVouchers() throws SQLException {
        LOGGER.debug("getAllVouchers()");
        return voucherDao.getAllVouchers();
    }

    @Override
    public Integer addVoucher (Voucher voucher)  throws SQLException {
         LOGGER.debug("addVoucher(): voucher country = {} ", voucher.getCountry());
        Assert.isNull(voucher.getVouchId(), "Voucher vouchId should be null.");
        Assert.hasText(voucher.getCountry(), "Voucher country should not be null.");
        Assert.notNull(voucher.getPrice(), "Voucher price should not be null");
        Assert.notNull(voucher.getDiscaunt(), "Voucher discaunt should not be null");

        return voucherDao.addVoucher(voucher);
    }

    @Override
    public Voucher getVoucherById(Integer vouchId) throws SQLException {
        LOGGER.debug("getVoucherById(): voucher id = {} ", vouchId);
        Assert.notNull(vouchId, "Voucher Id should not be null.");
        Assert.isTrue(vouchId > 0);
        return voucherDao.getVoucherById(vouchId);
    }

    @Override
    public void updateVoucher (Voucher voucher) throws SQLException {
        Assert.notNull(voucher, "Voucher should not be null.");
        LOGGER.debug("updateVoucher(): voucher country = {} ", voucher.getCountry());
        Assert.notNull(voucher.getVouchId(), "Voucher Id should not be null.");
        Assert.hasText(voucher.getCountry(), "Voucher country should not be null.");
        Assert.notNull(voucher.getPrice(), "Voucher country should not be null.");
        Assert.notNull(voucher.getDiscaunt(), "Voucher discaunt should not be null.");
        voucherDao.updateVoucher(voucher);
    }

    @Override
    public void deleteVoucher (Integer vouchId) throws SQLException {
        LOGGER.debug("deleteVoucher(): voucher id = {} ", vouchId);
        Assert.notNull(vouchId, "Voucher Id should not be null.");
        Assert.isTrue(vouchId > 0);
        voucherDao.deleteVoucher(vouchId);
    }

}

package com.zhenia.project.home.dao;

import com.zhenia.project.home.domain.Voucher;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yauheniya on 4.11.15.
 */
public interface VoucherDao {

    public List<Voucher> getAllVouchers() throws SQLException;

    public Voucher getVoucherById(Integer vouchId) throws SQLException;

    public Integer addVoucher (Voucher  voucher) throws SQLException;

    public void updateVoucher (Voucher voucher) throws SQLException;

    public void deleteVoucher (Integer vouchId) throws SQLException;

}
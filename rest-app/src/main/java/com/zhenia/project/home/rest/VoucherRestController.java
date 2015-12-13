package com.zhenia.project.home.rest;

import com.zhenia.project.home.domain.Voucher;
import com.zhenia.project.home.service.VoucherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yauheniya on 4.11.15.
 */

@RestController
public class VoucherRestController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private VoucherService voucherService;

    @RequestMapping(value = "/vouchers", method = RequestMethod.GET)
    public @ResponseBody List<Voucher> getAllVouchers() throws Exception {
        LOGGER.debug("getVouchers()");
        return voucherService.getAllVouchers();
    }

    @RequestMapping(value = "/voucher/insert", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addVoucher(@RequestBody Voucher voucher) throws SQLException {
        LOGGER.debug("addVoucher: user = {}", voucher);
        return voucherService.addVoucher(voucher);
    }

    @RequestMapping(value = "/update/voucher", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Voucher voucher) throws SQLException {
        Voucher dbVoucher = voucherService.getVoucherById(voucher.getVouchId());
        dbVoucher.setVouchId(voucher.getVouchId());
        dbVoucher.setCountry(voucher.getCountry());
        dbVoucher.setPrice(voucher.getPrice());
        dbVoucher.setDiscaunt(voucher.getDiscaunt());
        voucherService.updateVoucher(dbVoucher);
    }

    @RequestMapping(value = "/voucher/{vouchId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public @ResponseBody Voucher getVoucherById(@PathVariable(value = "vouchId") Integer vouchId) throws SQLException {
        LOGGER.debug("getVoucher: vouchId = {}", vouchId);
        return voucherService.getVoucherById(vouchId);
    }

    @RequestMapping(value = "/delete/voucher/{vouchId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable(value = "vouchId") Integer vouchId) throws SQLException {
        voucherService.deleteVoucher(vouchId);
    }
}





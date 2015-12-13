package com.zhenia.project.home.rest;


import com.zhenia.project.home.dto.VoucherDto;
import com.zhenia.project.home.service.VoucherDtoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yauheniya on 13.12.15.
 */
@RestController
public class VoucherDtoRestController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private VoucherDtoService voucherDtoService;

    @RequestMapping(value = "/voucherdto", method = RequestMethod.GET)
    public @ResponseBody
    List<VoucherDto> getAllVoucherDto() throws SQLException {
        LOGGER.debug("getVouchers()");
        return voucherDtoService.getAllVoucherDto();
    }

}

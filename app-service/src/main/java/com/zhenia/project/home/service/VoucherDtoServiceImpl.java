package com.zhenia.project.home.service;

import com.zhenia.project.home.dao.VoucherDtoDao;
import com.zhenia.project.home.dto.VoucherDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yauheniya on 13.12.15.
 */

@Transactional
public class VoucherDtoServiceImpl implements VoucherDtoService {
    private static final Logger LOGGER = LogManager.getLogger();

    private VoucherDtoDao voucherDtoDao;

    public void setVoucherDtoDao(VoucherDtoDao voucherDtoDao) {
        this.voucherDtoDao = voucherDtoDao;
    }

    @Override
    public List<VoucherDto> getAllVoucherDto() throws SQLException {
        LOGGER.debug("getAllVoucherDto()");
        return voucherDtoDao.getAllVoucherDto();
    }
}

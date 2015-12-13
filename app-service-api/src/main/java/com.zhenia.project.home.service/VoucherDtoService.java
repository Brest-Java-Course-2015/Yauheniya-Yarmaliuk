package com.zhenia.project.home.service;

import com.zhenia.project.home.dto.VoucherDto;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yauheniya on 13.12.15.
 */
public interface VoucherDtoService {
    public List<VoucherDto> getAllVoucherDto() throws SQLException;
}

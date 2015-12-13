package com.zhenia.project.home.dao;

import com.zhenia.project.home.dto.VoucherDto;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yauheniya on 13.12.15.
 */

public interface VoucherDtoDao {
    public List<VoucherDto> getAllVoucherDto() throws SQLException;
}

package com.zhenia.project.home.dao;

import com.zhenia.project.home.dto.VoucherDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.zhenia.project.home.dto.VoucherDto.VoucherDtoFields.*;

/**
 * Created by yauheniya on 13.12.15.
 */
public class VoucherDtoImpl implements VoucherDtoDao {
    private static final Logger LOGGER = LogManager.getLogger(VoucherDaoImpl.class);

    @Value("${voucherdto.count}")
    private String countdto;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public VoucherDtoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<VoucherDto> getAllVoucherDto() {
        LOGGER.info("getAllVoucherDto()");
        return namedParameterJdbcTemplate.query(countdto, new VoucherDtoRowMapper());
    }


    private class VoucherDtoRowMapper implements RowMapper<VoucherDto> {

        @Override
        public VoucherDto mapRow(ResultSet resultSet, int i) throws SQLException {
            VoucherDto voucherdto = new VoucherDto(resultSet.getInt(Voucher_ID.getValue()),
                    resultSet.getString(COUNTRY.getValue()),
                    resultSet.getInt(PRICE.getValue()),
                    resultSet.getInt(DISCAUNT.getValue()),
                    resultSet.getInt(COUNTPEOPL.getValue()));
            return voucherdto;
        }
    }


}

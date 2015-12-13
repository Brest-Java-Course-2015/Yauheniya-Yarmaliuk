package com.zhenia.project.home.dao;

import com.zhenia.project.home.domain.Voucher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhenia.project.home.domain.Voucher.VoucherFields.*;

/**
 * Created by yauheniya on 4.11.15.
 */
public class VoucherDaoImpl implements VoucherDao {
    private static final Logger LOGGER = LogManager.getLogger(VoucherDaoImpl.class);

    @Value("${voucher.select}")
    private String voucherSelect;

    @Value("${voucher.selectById}")
    private String voucherSelectById;

    @Value("${voucher.insert}")
    private String addVoucher;

    @Value("${voucher.delete}")
    private String deleteVoucher;

    @Value("${voucher.update}")
    private String updateVoucher;


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public VoucherDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Voucher> getAllVouchers() throws SQLException {
        LOGGER.info("getAllVouchers()");
        return namedParameterJdbcTemplate.query(voucherSelect, new VoucherRowMapper());
    }

    @Override
    public Voucher getVoucherById(Integer vouchId) throws SQLException {
        LOGGER.info("getVoucherById({})", vouchId);
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("vouchId", vouchId);
        return namedParameterJdbcTemplate.queryForObject(voucherSelectById, namedParameters,  new VoucherRowMapper());
    }

    @Override
    public void deleteVoucher(Integer vouchId) throws SQLException {
        LOGGER.info("deleteVoucher(): {}", vouchId);
        Map<String, Integer> namedParameters = new HashMap<String, Integer>();
        namedParameters.put("vouchId", vouchId);
        namedParameterJdbcTemplate.update(deleteVoucher, namedParameters);
    }

    @Override
    public void updateVoucher(Voucher voucher) throws SQLException {
        LOGGER.info("updateVoucher(voucher): {}", voucher.getCountry(), voucher.getPrice(), voucher.getDiscaunt());
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("vouchId", voucher.getVouchId());
        namedParameters.put("price", voucher.getPrice());
        namedParameters.put("discaunt", voucher.getDiscaunt());
        namedParameterJdbcTemplate.update(updateVoucher,namedParameters);
    }

    @Override
    public Integer addVoucher(Voucher voucher) throws SQLException {
        LOGGER.info("addVoucher(voucher): {}", voucher.getCountry());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addVoucher, getParametersMap(voucher), keyHolder);
        return keyHolder.getKey().intValue();
    }

    private MapSqlParameterSource getParametersMap(Voucher voucher) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(Voucher_ID.getValue(), voucher.getVouchId());
        parameterSource.addValue(COUNTRY.getValue(), voucher.getCountry());
        parameterSource.addValue(PRICE.getValue(), voucher.getPrice());
        parameterSource.addValue(DISCAUNT.getValue(), voucher.getDiscaunt());
        return parameterSource;
    }

    private class VoucherRowMapper implements RowMapper<Voucher> {

        @Override
        public Voucher mapRow(ResultSet resultSet, int i) throws SQLException {
            Voucher voucher = new Voucher(resultSet.getInt(Voucher_ID.getValue()),
                    resultSet.getString(COUNTRY.getValue()),
                    resultSet.getInt(PRICE.getValue()),
                    resultSet.getInt(DISCAUNT.getValue()));
            return voucher;
        }
    }
}
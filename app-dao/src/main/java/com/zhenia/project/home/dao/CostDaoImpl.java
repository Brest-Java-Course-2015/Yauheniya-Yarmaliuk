package com.zhenia.project.home.dao;

import com.zhenia.project.home.domain.Cost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static com.zhenia.project.home.domain.Cost.CostFields.*;
/**
 * Created by yauheniya on 6.11.15.
 */
public class CostDaoImpl implements CostDao {

    private static final Logger LOGGER = LogManager.getLogger(CostDaoImpl.class);

    @Value("${cost.select}")
    private String costSelect;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CostDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Cost> getAllCost() throws SQLException{
        LOGGER.info("getAllEmployees()");
        return namedParameterJdbcTemplate.query(costSelect, new CostRowMapper());
    }

    private class CostRowMapper implements RowMapper<Cost> {

        @Override
        public Cost mapRow(ResultSet resultSet, int i) throws SQLException {
            Cost cost = new Cost(
                    resultSet.getString(NAME.getValue()),
                    resultSet.getString(SURNAME.getValue()),
                    resultSet.getInt(YOURPRICE.getValue()));

            return cost;
        }
    }
}

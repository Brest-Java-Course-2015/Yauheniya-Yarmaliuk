package com.zhenia.project.home.service;

import com.zhenia.project.home.dao.CostDao;
import com.zhenia.project.home.domain.Cost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yauheniya on 6.11.15.
 */
public class CostServiceImpl implements CostService {
    private static final Logger LOGGER = LogManager.getLogger();

    private CostDao costDao;

    public void setCostDao(CostDao costDao) {
        this.costDao = costDao;
    }

    @Override
    public List<Cost> getAllCost() throws SQLException {
        LOGGER.debug("getAllCost()");
        return costDao.getAllCost();
    }
}

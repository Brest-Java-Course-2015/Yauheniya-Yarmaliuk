package com.zhenia.project.home.dao;

import com.zhenia.project.home.domain.Cost;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yauheniya on 6.11.15.
 */
public interface CostDao {

    public List<Cost> getAllCost() throws SQLException;

}

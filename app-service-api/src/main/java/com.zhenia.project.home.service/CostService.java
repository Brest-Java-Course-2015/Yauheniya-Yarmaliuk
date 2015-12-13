package com.zhenia.project.home.service;

import com.zhenia.project.home.domain.Cost;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yauheniya on 6.11.15.
 */
public interface CostService {

    public List<Cost> getAllCost() throws SQLException;
}

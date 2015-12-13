package com.zhenia.project.home.dao;


import com.zhenia.project.home.domain.Cost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by yauheniya on 6.11.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-test-dao.xml"})
@Transactional()
public class CostDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CostDao costDao;

    @Test
    public void testGetAllCost() throws SQLException {
        LOGGER.debug("test: getAllCost()");
        List<Cost> cost = costDao.getAllCost();
        assertTrue(cost.size() > 0);
    }
}

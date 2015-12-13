package com.zhenia.project.home.service;

import com.zhenia.project.home.dao.CostDao;
import com.zhenia.project.home.domain.Cost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Created by yauheniya on 6.11.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test-spring-service-mock.xml" })
public class CostServiceImplMockTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CostService costService;

    @Autowired
    private CostDao mockCostDao;

    @After
    public void clean() {
        verify(mockCostDao);
        reset(mockCostDao);
    }

    @Test
    public void testGetAllCost() throws SQLException {
        List<Cost> cost = new ArrayList();
        expect(mockCostDao.getAllCost()).andReturn(cost);
        replay(mockCostDao);
        int id = costService.getAllCost().size();
        Assert.assertTrue(id == cost.size());
    }
}

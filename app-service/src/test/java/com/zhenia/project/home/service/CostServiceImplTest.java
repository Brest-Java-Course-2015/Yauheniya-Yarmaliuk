package com.zhenia.project.home.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yauheniya on 6.11.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class CostServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CostService costService;

    @Test
    public void testGetAllCost() throws Exception {
        LOGGER.debug("test: getAllCost()");
        Assert.assertTrue(costService.getAllCost().size() > 0);
    }

}

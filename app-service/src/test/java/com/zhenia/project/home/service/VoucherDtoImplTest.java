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
 * Created by yauheniya on 13.12.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class VoucherDtoImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private VoucherDtoService voucherDtoService;


    @Test
    public void testGetAllVoucherDto() throws Exception {
        LOGGER.debug("test: getAllVoucherDto()");
        Assert.assertTrue(voucherDtoService.getAllVoucherDto().size() > 0);
    }

}

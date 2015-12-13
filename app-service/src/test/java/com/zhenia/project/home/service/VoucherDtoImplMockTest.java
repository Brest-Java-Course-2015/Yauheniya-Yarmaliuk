package com.zhenia.project.home.service;


import com.zhenia.project.home.dao.VoucherDtoDao;
import com.zhenia.project.home.dto.VoucherDto;
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
 * Created by yauheniya on 13.12.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test-spring-service-mock.xml" })
public class VoucherDtoImplMockTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private VoucherDtoService voucherDtoService;

    @Autowired
    private VoucherDtoDao mockVoucherDtoDao;

    @After
    public void clean() {
        verify(mockVoucherDtoDao);
        reset(mockVoucherDtoDao);
    }

    @Test
    public void testGetAllVoucherDto() throws SQLException {
        List<VoucherDto> voucherdto = new ArrayList();
        expect(mockVoucherDtoDao.getAllVoucherDto()).andReturn(voucherdto);
        replay(mockVoucherDtoDao);
        int id = voucherDtoService.getAllVoucherDto().size();
        Assert.assertTrue(id == voucherdto.size());
    }

}

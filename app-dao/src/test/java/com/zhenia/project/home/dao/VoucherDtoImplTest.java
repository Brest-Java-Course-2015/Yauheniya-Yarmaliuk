package com.zhenia.project.home.dao;

import com.zhenia.project.home.dto.VoucherDto;
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
 * Created by yauheniya on 13.12.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-test-dao.xml"})
@Transactional()
public class VoucherDtoImplTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private VoucherDtoDao voucherDtoDao;

    @Test
    public void getAllVoucherDto() throws SQLException {
        LOGGER.debug("test: getAllVoucherDto()");
        List<VoucherDto> voucherdto = voucherDtoDao.getAllVoucherDto();
        assertTrue(voucherdto.size() > 0);
    }

}

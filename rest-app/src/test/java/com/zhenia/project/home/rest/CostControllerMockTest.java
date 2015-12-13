package com.zhenia.project.home.rest;

import com.zhenia.project.home.domain.Cost;
import com.zhenia.project.home.service.CostService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by yauheniya on 6.11.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-rest-mock.xml"})
public class CostControllerMockTest {

    @Resource
    private CostRestController costController;

    private MockMvc mockMvc;

    @Autowired
    private CostService costService;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(costController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(costService);
        reset(costService);
    }

    @Test
    public void getAllCostTest() throws Exception {
        expect(costService.getAllCost()).andReturn(Arrays.<Cost>asList(new Cost()));
        replay(costService);

        mockMvc.perform(
                get("/cost")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }
}
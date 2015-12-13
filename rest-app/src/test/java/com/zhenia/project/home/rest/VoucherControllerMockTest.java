package com.zhenia.project.home.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhenia.project.home.domain.Voucher;
import com.zhenia.project.home.service.VoucherService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by yauheniya on 5.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-rest-mock.xml"})
public class VoucherControllerMockTest {

    @Resource
    private VoucherRestController voucherController;

    private MockMvc mockMvc;

    @Autowired
    private VoucherService voucherService;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(voucherController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(voucherService);
        reset(voucherService);
    }

    @Test
    public void addVoucherTest() throws Exception {
        expect(voucherService.addVoucher(anyObject(Voucher.class))).andReturn(3);
        replay(voucherService);

        String voucher = new ObjectMapper().writeValueAsString(new Voucher("Japany", 990, 30));

        mockMvc.perform(
                post("/voucher/insert")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voucher)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("3"));
    }



    @Test
    public void getVouchersTest() throws Exception {
        expect(voucherService.getAllVouchers()).andReturn(Arrays.<Voucher>asList(new Voucher("Japany", 990, 30)));
        replay(voucherService);

        mockMvc.perform(
                get("/vouchers")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteVoucherTest() throws Exception {
        voucherService.deleteVoucher(anyObject(Integer.class));
        expectLastCall();
        replay(voucherService);

        mockMvc.perform(
                delete("/delete/voucher/3")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(""));
    }

    @Test
    public void getVoucherByIdTest() throws Exception {
        expect(voucherService.getVoucherById(anyObject(Integer.class))).andReturn(new Voucher(1,"Egypt", 800, 30));
        replay(voucherService);
        mockMvc.perform(
                get("/voucher/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isFound());
    }

}
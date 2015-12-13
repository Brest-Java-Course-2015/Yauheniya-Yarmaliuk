package com.zhenia.project.home.rest;

import com.zhenia.project.home.domain.Cost;
import com.zhenia.project.home.service.CostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yauheniya on 6.11.15.
 */

@RestController
public class CostRestController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CostService costService;

    @RequestMapping(value = "/cost", method = RequestMethod.GET)
    public List<Cost> getAllCost() throws Exception {
        LOGGER.debug("getAllCost()");
        return costService.getAllCost();
    }
}

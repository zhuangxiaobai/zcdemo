package com.example.zcdemo03.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/logDemo")
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/test")
    public void test(){
        logger.info("进入test方法");
        logger.debug("进入test方法");
        logger.warn("进入test方法");
        logger.error("进入test方法");
    }
}
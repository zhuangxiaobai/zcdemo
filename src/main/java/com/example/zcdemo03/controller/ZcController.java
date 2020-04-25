package com.example.zcdemo03.controller;


import com.example.zcdemo03.api.CommonResult;
import com.example.zcdemo03.service.ZcService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zcTest")
public class ZcController {
    @Autowired
    private ZcService zcService;
    @ApiOperation("给小程序获取姓名的接口")
    @RequestMapping(value = "/search")
    @ResponseBody
    public CommonResult searchTest() {

        List<String> names = zcService.getName();
        Map<String, List<String>> tokenMap = new HashMap<>();
        tokenMap.put("token", names);
        return CommonResult.success(tokenMap);
    }












}

package com.example.zcdemo03.controller;

import com.example.zcdemo03.api.CommonResult;
import com.example.zcdemo03.bean.User;
import com.example.zcdemo03.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "UserController", description = "用户操作")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ApiOperation("添加")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody  @ApiParam("用户信息") User user) {
        CommonResult commonResult;
        int count = userService.create(user);
        if (count == 1) {
            commonResult = CommonResult.success(user);
            LOGGER.debug("create success:{}", user);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("create failed:{}", user);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id的用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id") @ApiParam("用户ID") Long id, @RequestBody User user, BindingResult result) {
        CommonResult commonResult;
        int count = userService.update(id, user);
        if (count == 1) {
            commonResult = CommonResult.success(user);
            LOGGER.debug("update success:{}", user);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("update failed:{}", user);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = userService.delete(id);
        if (count == 1) {
            LOGGER.debug("delete success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("delete failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("获取指定id的用户详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<User> getItem(@PathVariable("id") Long id) {
        return CommonResult.success(userService.getItem(id));
    }
}

package com.example.zcdemo03.controller;



import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.example.zcdemo03.api.CommonResult;
import com.example.zcdemo03.bean.User;
import com.example.zcdemo03.service.RedisService;
import com.example.zcdemo03.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/redis")
public class RedisController {

   //系统默认的StringRedisTemplete
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    //添加
    @ApiOperation("测试字符串存")
    @RequestMapping(value = "/set")
    public void saveRedis() {
     //   stringRedisTemplate.opsForValue().set("test-key", "test-value");
       User user = new User();
       user.setName("sss");
       user.setAge("11");
        redisTemplate.opsForValue().set("zz",user);

    }

    //获取
    @ApiOperation("测试字符串取")
    @RequestMapping(value = "/get")
    public String getRedis() {
        return stringRedisTemplate.opsForValue().get("test-key");

      //  return redisTemplate.opsForValue().get("zc");
    }



    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;


    @ApiOperation("测试简单缓存")
    @RequestMapping(value = "/simpleTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<User> simpleTest() {
        List<User> userList = userService.getAll();
        User user = userList.get(0);
        String key = "redis:simple:" + user.getId();
        redisService.set(key, user);
        User cacheUser = (User) redisService.get(key);
        return CommonResult.success(cacheUser);
    }

   @ApiOperation("测试Hash结构的缓存")
    @RequestMapping(value = "/hashTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<User> hashTest() {
        List<User> userList = userService.getAll();
        User user = userList.get(0);
        String key = "redis:hash:" + user.getId();
        Map<String, Object> value = BeanUtil.beanToMap(user);
        redisService.hSetAll(key, value);
        Map<Object, Object> cacheValue = redisService.hGetAll(key);
        User cacheUser = BeanUtil.mapToBean(cacheValue, User.class, true);
        return CommonResult.success(cacheUser);
    }

    @ApiOperation("测试Set结构的缓存")
    @RequestMapping(value = "/setTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Set<Object>> setTest() {
         List<User> userList = userService.getAll();
        String key = "redis:set:all";
        redisService.sAdd(key, (Object[]) ArrayUtil.toArray(userList, User.class));
        redisService.sRemove(key, userList.get(0));
        Set<Object> cachedUserList = redisService.sMembers(key);
        return CommonResult.success(cachedUserList);
   }

    @ApiOperation("测试List结构的缓存")
    @RequestMapping(value = "/listTest", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Object>> listTest() {
        List<User> userList = userService.getAll();
        String key = "redis:list:all";
        redisService.lPushAll(key, (Object[]) ArrayUtil.toArray(userList, User.class));
        redisService.lRemove(key, 1, userList.get(0));
        List<Object> cachedUserList = redisService.lRange(key, 0, 3);
        return CommonResult.success(cachedUserList);
    }


}

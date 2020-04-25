package com.example.zcdemo03.service.serviceImpl;

import com.example.zcdemo03.bean.User;
import com.example.zcdemo03.config.RedisConfig;
import com.example.zcdemo03.mapper.UserMapperDao;
import com.example.zcdemo03.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapperDao userMapper;

    @Override
    public int create(User user) {
        return userMapper.insertSelective(user);
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:user:'+#id")
    @Override
    public int update(Long id, User user) {
        user.setId(id);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:user:'+#id")
    @Override
    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
}

    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:user:'+#id", unless = "#result==null")
    @Override
    public User getItem(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectAllUser();
    }





}

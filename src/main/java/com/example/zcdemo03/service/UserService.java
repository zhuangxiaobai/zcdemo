package com.example.zcdemo03.service;

import com.example.zcdemo03.bean.User;

import java.util.List;

public interface UserService {

    int create(User user);

    int update(Long id, User user);

    int delete(Long id);

    User getItem(Long id);

    List<User> getAll();

    // List<User> list(Integer pageNum, Integer pageSize);

    //List<User> ListAll();


}

package com.us.example.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.us.example.model.bean.User;
import com.us.example.mapper.UserDao;
import com.us.example.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.selectAll();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        int i = userDao.update(user);
        if(i>0){
            throw new RuntimeException("回滚测试");
        }
    }

    @Override
    public void insertUser(User user) {
        userDao.insert(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }


}
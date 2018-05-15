package com.us.example.service.impl;


import com.alicp.jetcache.Cache;
import com.alicp.jetcache.RefreshPolicy;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.embedded.LinkedHashMapCacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.us.example.model.bean.User;
import com.us.example.mapper.UserDao;
import com.us.example.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @CreateCache(expire = 100,cacheType = CacheType.LOCAL)
    private Cache<Integer, User> userCache;

    // 自动刷新
    RefreshPolicy policy = RefreshPolicy.newPolicy(1, TimeUnit.MINUTES)
            .stopRefreshAfterLastAccess(30, TimeUnit.MINUTES);

    Cache<Integer, User> orderSumCache = LinkedHashMapCacheBuilder.createLinkedHashMapCacheBuilder()
            .loader(this::loadUserFromDatabase)
            .refreshPolicy(policy)
            .buildCache();

    @PostConstruct
    public void init() {
        userCache.config().setLoader(this::loadUserFromDatabase);
    }

    private User loadUserFromDatabase(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> getAll() {
        return userDao.selectAll();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        int i = userDao.update(user);
        if (i > 0) {
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

    @Override
    public User getUserByIdCache(Integer userId) {
        return userCache.get(userId);
    }


}
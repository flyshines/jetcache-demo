package com.us.example.service;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.us.example.model.bean.User;

import java.util.List;

/**
 * The Interface UserService.
 */
public interface UserService {

    //@Cached(name = "userCache", expire = 3600, cacheType = CacheType.REMOTE)
    List<User> getAll();

    //@CacheUpdate(name = "userCache.", key = "#user.userId", value = "#user")
    //@CacheInvalidate(name="userCache.", key = "#user.userId")
    void updateUser(User user);

    void insertUser(User user);

    //@Cached(name = "userCache.", key = "#userId", expire = 3600, cacheType = CacheType.REMOTE)
    User getUserById(Integer userId);
}
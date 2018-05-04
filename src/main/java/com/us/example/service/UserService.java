package com.us.example.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.us.example.model.dto.UserDTO;
import com.us.example.model.vo.UserVO;

/**
 * The Interface UserService.
 */
public interface UserService {

    @Cached(name = "User:getAll", key = "#id", expire = 3600, cacheType = CacheType.REMOTE)
    UserVO getAll();

    @CacheUpdate(name = "User:update", key = "#userDto.id", value = "#userDto")
    void update(UserDTO userDto);

    @Cached(name = "User:getInfo", key = "#id", expire = 3600, cacheType = CacheType.REMOTE)
    UserVO getInfo(Integer id);
}
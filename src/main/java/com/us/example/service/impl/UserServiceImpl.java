package com.us.example.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.us.example.model.bean.User;
import com.us.example.model.dto.UserDTO;
import com.us.example.model.vo.UserVO;
import com.us.example.mapper.UserDao;
import com.us.example.service.UserService;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserVO getAll() {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDao.selectAll(), userVO);
        return userVO;
    }

    @Override
    public void update(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userDao.update(user);
    }

    @Override
    public UserVO getInfo(Integer id) {
        return null;
    }


}
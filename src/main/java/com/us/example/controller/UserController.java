package com.us.example.controller;

import com.us.example.model.bean.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.us.example.service.UserService;

import java.util.Date;

/**
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /**
     * 查询全部
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> listUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    /**
     * 根据ID查询
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> getUserInfo(@RequestParam() Integer id) {
        //return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        return new ResponseEntity<>(userService.getUserByIdCache(id), HttpStatus.OK);
    }

    /**
     * 更新
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> updateUserInfo(@RequestParam() Integer id, @RequestParam() String name) {
        User user = new User();
        user.setUserId(id);
        user.setName(name);
        user.setCreateTime(new Date());
        userService.updateUser(user);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    /**
     * 插入
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> insertUser(@RequestParam() String name) {
        User user = new User();
        user.setName(name);
        user.setCreateTime(new Date());
        userService.insertUser(user);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
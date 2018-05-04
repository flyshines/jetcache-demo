package com.us.example.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
import com.us.example.util.CommonUtil;

/**
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /***
     * @param request
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> listUsers(HttpServletRequest request) {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    /***
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> getUserInfo(HttpServletRequest request,@RequestParam() Integer id) {
        return new ResponseEntity<>(userService.getInfo(id), HttpStatus.OK);
    }


}
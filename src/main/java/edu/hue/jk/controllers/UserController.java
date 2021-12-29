package edu.hue.jk.controllers;

import edu.hue.jk.mappers.UserMapper;
import edu.hue.jk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    @ResponseBody
    //登录模块
    public String login(String username, String password) {
        User loginUser = userMapper.login(username, password);
        if (loginUser != null) {
            return loginUser.toString();
        } else {
            return "用户名或者密码错误！";
        }
    }

    //注册模块
    @GetMapping("/register")
    @ResponseBody
    public String register(String username, String password) {
        if (userMapper.getUserByName(username) == null) {
            int isRegister = userMapper.register(username, password);
            if (isRegister > 0) {
                return "注册成功!";
            } else {
                return "注册失败!";
            }
        } else
            return "注册失败，用户名重复！";
    }
}

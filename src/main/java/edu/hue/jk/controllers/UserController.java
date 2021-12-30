package edu.hue.jk.controllers;

import edu.hue.jk.mappers.UserMapper;
import edu.hue.jk.models.User;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
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
            return "注册失败，当前用户名已被注册！";
    }

    //根据用户名查找用户信息
    @GetMapping("/selectUserInfoByUserid")
    @ResponseBody
    public String selectUserInfoByUserid(String username) {
        User userinfo = userMapper.getUserByName(username);
        return userinfo.toString();
    }

    //删除用户
    @GetMapping("/delUserInfo")
    @ResponseBody
    public String delUserInfo(String username, String password){
        int delUserInfoByNameandPwd = userMapper.del(username, password);
        if (delUserInfoByNameandPwd > 0){
            return "该用户已成功删除！";
        }else{
            return "删除用户失败！";
        }
    }

}

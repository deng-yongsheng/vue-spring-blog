package edu.hue.jk.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hue.jk.mappers.UserMapper;
import edu.hue.jk.models.User;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/login")
    @ResponseBody
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public String login(@RequestParam String username, @RequestParam String password) throws JsonProcessingException {
        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            return "用户和密码不能为空！";
        }
        User loginUser = userMapper.login(username, password);
        if (loginUser != null) {
            return objectMapper.writeValueAsString(loginUser);
        } else {
            return "用户名或者密码错误！";
        }
    }
    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @GetMapping("/register")
    @ResponseBody
    public String register( String username,  String password) {
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
    /**
     * 判断用户是否存在
     * @param username 用户名
     * @return
     */
    @GetMapping("/exists")
    @ResponseBody
    public Boolean exists(String username) {
        if (userMapper.getUserByName(username) == null) {
            return true;
        } else {
            return false;
        }
    }

    //根据用户名查找用户信息
    @GetMapping("/selectUserInfoByUserid")
    @ResponseBody
    public String selectUserInfoByUserid(String username) throws JsonProcessingException {
        User userinfo = userMapper.getUserByName(username);
        return objectMapper.writeValueAsString(userinfo);
    }

    //删除用户
    @GetMapping("/delUserInfo")
    @ResponseBody
    public String delUserInfo(String username, String password) {
        int delUserInfoByNameandPwd = userMapper.del(username, password);
        if (delUserInfoByNameandPwd > 0) {
            return "该用户已成功删除！";
        } else {
            return "删除用户失败！";
        }
    }

}

package edu.hue.jk.controllers;

import edu.hue.jk.mappers.UserMapper;
import edu.hue.jk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    @ResponseBody
    public String login(Model model, String username, String password) {
        User loginUser = userMapper.login(username, password);
        if (loginUser != null) {
            return loginUser.toString();
        } else {
            return "用户名或者密码错误！";
        }
    }
}

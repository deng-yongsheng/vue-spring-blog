package edu.hue.jk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "forward:/index.html";
    }

    @RequestMapping("/login")
    public String login() {
        return "forward:/login.html";
    }

    @RequestMapping("/register")
    public String register() {
        return "forward:/register.html";
    }

    @RequestMapping("/upload")
    public String upload() {
        return "forward:/upload.html";
    }

    @RequestMapping("/user/{userid}")
    public String user(Model model, @PathVariable(value = "userid") Integer userid) {
        model.addAttribute("userid", userid);
        return "user.html";
    }
}

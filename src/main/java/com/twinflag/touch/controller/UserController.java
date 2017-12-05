package com.twinflag.touch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "账户名或者密码错误");
        }
        if (logout != null){
            model.addAttribute("message", "账户已经退出");
        }
        return "login";
    }

    @RequestMapping("/home")
    public String home() {
        return "redirect:/program/getPrograms";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }
}

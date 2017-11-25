package com.twinflag.touch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProgramController {

    @RequestMapping("/login")
    @ResponseBody
    public String login() {
        return "login";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello";
    }

    @RequestMapping("/hi")
    @ResponseBody
    public String nihao() {
        return "nihao";
    }
}

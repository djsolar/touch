package com.twinflag.touch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourceController {

    @RequestMapping("/resourceManage")
    public String resourceManage() {
        return "resource_manage";
    }
}

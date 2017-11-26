package com.twinflag.touch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

    @RequestMapping("/templateManage")
    public String templateManage() {

        return "template_manage";
    }
}

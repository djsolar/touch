package com.twinflag.touch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProgramController {

    @RequestMapping("/program")
    public String program() {
        return "program";
    }

    @RequestMapping("/hi")
    @ResponseBody
    public String nihao() {
        return "nihao";
    }
}

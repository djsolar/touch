package com.twinflag.touch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProgramController {


    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mad = new ModelAndView("index");
        return mad;
    }
}

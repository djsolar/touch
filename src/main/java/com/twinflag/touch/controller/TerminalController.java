package com.twinflag.touch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/terminal")
public class TerminalController {

    @RequestMapping("/terminalManage")
    public String terminalManage() {
        return "terminalManage";
    }

    @RequestMapping("/terminalStatus")
    public String terminalStatus() {
        return "terminalStatus";
    }
}

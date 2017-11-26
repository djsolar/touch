package com.twinflag.touch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TerminalController {

    @RequestMapping("/terminalManage")
    public String terminalManage() {
        return "terminal_manage";
    }

    @RequestMapping("/terminalStatus")
    public String terminalStatus() {
        return "terminal_status";
    }
}

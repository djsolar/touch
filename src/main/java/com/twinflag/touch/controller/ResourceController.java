package com.twinflag.touch.controller;

import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.service.AchieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private AchieveService achieveService;

    @RequestMapping("/resourceManage")
    public String resourceManage(Model model) {
        List<Achieve> achieves = achieveService.findAll();
        model.addAttribute("achieves", achieves);
        return "resource";
    }

    @RequestMapping(value = "/addAchieve", method = RequestMethod.POST)
    @ResponseBody
    public boolean addAchieve(HttpServletRequest request) {
        String achieveName = request.getParameter("achieve_name");
        String achieveAuthority = request.getParameter("achieve_authority");
        Achieve achieve = new Achieve();
        achieve.setName(achieveName);
        achieve.setAuthority(Integer.parseInt(achieveAuthority));
        achieve.setCreateDate(new Date());
        achieveService.saveAchieve(achieve);
        return true;
    }
}

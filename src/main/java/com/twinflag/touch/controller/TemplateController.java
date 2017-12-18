package com.twinflag.touch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinflag.touch.entity.DataTableViewPage;
import com.twinflag.touch.model.Program;
import com.twinflag.touch.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @RequestMapping("/getTemplates")
    public String templateManage() {
        return "template";
    }

    @RequestMapping(value = "/getTemplateData", method = RequestMethod.GET)
    @ResponseBody
    public String getTemplateData(HttpServletRequest request) throws JsonProcessingException {
        int start = Integer.parseInt(request.getParameter("start"));
        int pageSize = Integer.parseInt(request.getParameter("length"));
        System.out.println("start = " + start + ", pageSize = " + pageSize);
        int page = start / pageSize;
        DataTableViewPage<Program> t = templateService.findAllTemplate(page, pageSize);
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(t);
        return message;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean deleteTemplate(Integer id) {
        return templateService.deleteTemplate(id);
    }

    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public String uploadTemplate(@RequestParam("file")MultipartFile file) {
        String filename = file.getOriginalFilename();
        try {
            templateService.uploadTemplate(file, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/template/getTemplates";
    }


}

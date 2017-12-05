package com.twinflag.touch.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.twinflag.touch.model.Program;
import com.twinflag.touch.respository.ProgramRepository;
import com.twinflag.touch.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @RequestMapping("/getTemplates")
    public String templateManage() {
        return "template";
    }

    @JsonView(DataTablesOutput.View.class)
    @RequestMapping(value = "/getTemplateData", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesOutput<Program> getTemplateData(@Valid DataTablesInput data , BindingResult bindingResult) {
        return templateService.findAllTemplate(data);
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

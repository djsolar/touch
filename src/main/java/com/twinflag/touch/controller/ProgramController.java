package com.twinflag.touch.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.twinflag.touch.model.Program;
import com.twinflag.touch.service.ProgramService;
import com.twinflag.touch.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/program")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @Autowired
    private TemplateService templateService;

    /**
     * 获取节目单
     *
     * @return
     */
    @RequestMapping("/getPrograms")
    public String getPrograms(Model model) {
        List<Object> templates = templateService.getTemplateInfos();
        Map<String, String> templateMap = new HashMap<>();
        for(Object p : templates) {
            System.out.println(p.getClass().getSimpleName());
            Object[] array = (Object[]) p;
            templateMap.put(String.valueOf(array[0]), String.valueOf(array[1]));
        }
        model.addAttribute("templateMap", templateMap);
        return "program";
    }


    @RequestMapping(value = "/getProgramData", method = RequestMethod.GET)
    @ResponseBody
    @JsonView({DataTablesOutput.View.class})
    public DataTablesOutput<Program> getProgramData(@Valid DataTablesInput input, BindingResult bindingResult) {
        return programService.findAll(input);
    }

    /**
     * 创建新的节目单
     *
     * @return
     */
    @RequestMapping(value = "/createProgram", method = {RequestMethod.POST})
    public String createProgram() {
        return "program_edit";
    }
}

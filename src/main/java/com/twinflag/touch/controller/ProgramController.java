package com.twinflag.touch.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.twinflag.touch.entity.ContentBean;
import com.twinflag.touch.model.*;
import com.twinflag.touch.service.ProgramService;
import com.twinflag.touch.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    @RequestMapping(value = "/createProgram/{id}", method = {RequestMethod.GET})
    public String createProgram(@PathVariable Integer id, Model model) {
        Program program = templateService.findTemplate(id);
        try {
            String programData = transferProgramJSON(program);
            model.addAttribute("programData", programData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "programNew";
    }

    public String transferProgramJSON(Program program) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<LevelOne> levelOnes = program.getLevelOnes();
        ArrayNode levelOneNodes = mapper.createArrayNode();
        for(LevelOne levelOne : levelOnes) {
            ObjectNode levelOneNode = mapper.createObjectNode();
            levelOneNode.put("text","level-1");
            levelOneNode.put("type", "level1");
            levelOneNode.put("iconurl_a", "/" + program.getProgramName() + "/" + levelOne.getNormalPic());
            levelOneNode.put("iconurl_b", "/" + program.getProgramName() + "/" + levelOne.getSelectedPic());
            List<LevelTwo> levelTwos = levelOne.getLevelTwos();
            ArrayNode levelTwoNodes = mapper.createArrayNode();
            for(LevelTwo levelTwo : levelTwos) {
                ObjectNode levelTwoNode = mapper.createObjectNode();
                levelTwoNode.put("text", levelTwo.getLabel());
                levelTwoNode.put("title", levelTwo.getLabel());
                levelTwoNode.put("ismany", levelTwo.isMany());
                levelTwoNode.put("title", levelTwo.getTitle());
                levelTwoNode.put("url", levelTwo.getUrl());
                levelTwoNode.put("type", "level-two");
                List<Content> contents = levelTwo.getContents();
                if (contents.size() > 0) {
                    ArrayNode contentNodes = mapper.createArrayNode();
                    for(Content content : contents) {
                        ObjectNode contentNode = mapper.createObjectNode();
                        String title = content.getTitle() == null ? content.getTitle(): "content";
                        contentNode.put("text", title);
                        contentNode.put("title", content.getTitle());
                        contentNode.put("type", "");
                        List<Source> sources = content.getSources();
                        if (sources.size() == 1) {
                            contentNode.put("url", sources.get(0).getRelativePath());
                        } else {
                            ArrayNode sourceNode = mapper.createArrayNode();
                            for(Source source : sources) {
                                sourceNode.add(source.getRelativePath());
                            }
                            contentNode.set("urls", sourceNode);
                        }
                        contentNodes.add(contentNode);
                    }
                    levelTwoNode.set("nodes", contentNodes);
                }
                levelTwoNodes.add(levelTwoNode);
            }
            levelOneNode.set("nodes", levelTwoNodes);
            levelOneNodes.add(levelOneNode);
        }
        return mapper.writeValueAsString(levelOneNodes);
    }
}

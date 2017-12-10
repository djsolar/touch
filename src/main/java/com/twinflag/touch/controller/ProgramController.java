package com.twinflag.touch.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinflag.touch.entity.ContentBean;
import com.twinflag.touch.entity.LevelOneBean;
import com.twinflag.touch.entity.LevelTwoBean;
import com.twinflag.touch.model.*;
import com.twinflag.touch.service.ProgramService;
import com.twinflag.touch.service.TemplateService;
import com.twinflag.touch.tree.TreeLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    @RequestMapping(value = "/createProgram/{id}", method = {RequestMethod.GET})
    public String createProgram(@PathVariable Integer id, Model model) {
        Program program = templateService.findTemplate(id);
        try {
            String programData2 = transferProgramTree(program);
            System.out.println(programData2);
            model.addAttribute("programData", programData2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "programNew";
    }

    public String transferProgramTree(Program program) throws JsonProcessingException {
        List<LevelOne> levelOnes = program.getLevelOnes();
        List<TreeLevel> treeLevelOnes = new ArrayList<>();
        for(LevelOne levelOne : levelOnes) {
            LevelOneBean levelOneBean = new LevelOneBean();
            levelOneBean.setNormalPic(levelOne.getNormalPic().getAbsolutePath());
            levelOneBean.setSelectedPic(levelOne.getSelectedPic().getAbsolutePath());
            TreeLevel treeLevel = new TreeLevel();
            treeLevel.setText("Level-1");
            treeLevel.setType(0);
            treeLevel.setData(levelOneBean);
            List<LevelTwo> levelTwos = levelOne.getLevelTwos();
            List<TreeLevel> treeLevelTwos = new ArrayList<>();
            for(LevelTwo levelTwo : levelTwos) {
                LevelTwoBean levelTwoBean = new LevelTwoBean();
                levelTwoBean.setLabel(levelTwo.getLabel());
                levelTwoBean.setTitle(levelTwo.getTitle());
                levelTwoBean.setMany(levelTwo.isMany());
                levelTwoBean.setUrl(levelTwo.getUrl());
                TreeLevel treeLevelTwo = new TreeLevel();
                treeLevelTwo.setText(levelTwo.getLabel());
                treeLevelTwo.setType(1);
                treeLevelTwo.setData(levelTwoBean);
                List<Content> contents = levelTwo.getContents();
                List<TreeLevel> treeLevelContents = new ArrayList<>();
                if (contents.size() > 0) {
                    for(Content content : contents) {
                        TreeLevel treeLevelContent = new TreeLevel();
                        ContentBean contentBean = new ContentBean();
                        contentBean.setType(content.getType());
                        contentBean.setLabel(content.getLabel());
                        contentBean.setTitle(contentBean.getTitle());
                        List<Source> sources = content.getSources();
                        List<String> paths = new ArrayList<>();
                        for(Source source : sources) {
                            paths.add(source.getAbsolutePath());
                        }
                        contentBean.setPaths(paths);
                        String title = content.getTitle() != null ? content.getTitle(): "content";
                        treeLevelContent.setText(title);
                        treeLevelContent.setType(2);
                        treeLevelContent.setData(contentBean);
                        treeLevelContents.add(treeLevelContent);
                    }
                    treeLevelTwo.setNodes(treeLevelContents);
                }
                treeLevelTwos.add(treeLevelTwo);
            }
            treeLevel.setNodes(treeLevelTwos);
            treeLevelOnes.add(treeLevel);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(treeLevelOnes);
    }
}

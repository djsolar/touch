package com.twinflag.touch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinflag.touch.entity.*;
import com.twinflag.touch.model.*;
import com.twinflag.touch.service.ProgramService;
import com.twinflag.touch.service.TemplateService;
import com.twinflag.touch.tree.TreeLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

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
        for (Object p : templates) {
            System.out.println(p.getClass().getSimpleName());
            Object[] array = (Object[]) p;
            templateMap.put(String.valueOf(array[0]), String.valueOf(array[1]));
        }
        model.addAttribute("templateMap", templateMap);
        return "program";
    }

    @RequestMapping(value = "/saveProgram", method = {RequestMethod.POST})
    @ResponseBody
    public boolean saveProgram(String programName, String program) {
        try {
            programService.saveProgram(programName, program);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/updateProgram", method = {RequestMethod.POST})
    @ResponseBody
    public boolean changeProgram(Integer programId, String program) {
        try {
            programService.updateProgram(programId, program);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/deleteProgram/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public boolean deleteProgram(@PathVariable Integer id) {
        programService.deleteProgram(id);
        return true;
    }

    @RequestMapping(value = "/publishProgram/{id}")
    public boolean publishProgram(@PathVariable Integer id) {
        Program program = programService.findProgram(id);
        String zipPath = program.getZipPath();
        return true;
    }


    @RequestMapping("/programExist")
    @ResponseBody
    public boolean isProgramExist(String programName) {
        if (StringUtils.isEmpty(programName))
            return false;
        return programService.isProgramNameExist(programName);
    }


    @RequestMapping(value = "/getProgramData", method = RequestMethod.GET)
    @ResponseBody
    public DataTableViewPage<Program> getProgramData(HttpServletRequest request) {
        int page = 0;
        int pageSize = 10;
        return programService.findAll(page, pageSize);
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
            String programData = transferProgramTree(program);
            model.addAttribute("programData", programData);
            model.addAttribute("isEdit", "false");
            model.addAttribute("id", id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "programNew";
    }

    @RequestMapping(value = "/editProgram/{id}", method = {RequestMethod.GET})
    public String editProgram(@PathVariable Integer id, Model model) {
        Program program = programService.findProgram(id);
        try {
            String programData = transferProgramTree(program);
            model.addAttribute("programData", programData);
            model.addAttribute("isEdit", "true");
            model.addAttribute("id", id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "programNew";
    }


    public String transferProgramTree(Program program) throws JsonProcessingException {
        List<LevelOne> levelOnes = program.getLevelOnes();
        List<TreeLevel> treeLevelOnes = new ArrayList<>();
        for (LevelOne levelOne : levelOnes) {
            LevelOneBean levelOneBean = new LevelOneBean();
            levelOneBean.setId(levelOne.getId());
            Material normalPic = levelOne.getNormalPic();
            MaterialBean normalMaterial = new MaterialBean();
            normalMaterial.setOriginName(normalPic.getOriginName());
            normalMaterial.setMd5Name(normalPic.getMacName());
            normalMaterial.setId(normalPic.getId());
            levelOneBean.setNormalMaterial(normalMaterial);

            Material selectedPic = levelOne.getSelectedPic();
            MaterialBean selectedMaterial = new MaterialBean();
            selectedMaterial.setOriginName(selectedPic.getOriginName());
            selectedMaterial.setMd5Name(selectedPic.getMacName());
            selectedMaterial.setId(selectedPic.getId());
            levelOneBean.setSelectedMaterial(selectedMaterial);
            levelOneBean.setMediaType(1);
            TreeLevel treeLevel = new TreeLevel();
            treeLevel.setText("Level-1");
            treeLevel.setType(0);
            treeLevel.setData(levelOneBean);
            List<LevelTwo> levelTwos = levelOne.getLevelTwos();
            List<TreeLevel> treeLevelTwos = new ArrayList<>();
            for (LevelTwo levelTwo : levelTwos) {
                LevelTwoBean levelTwoBean = new LevelTwoBean();
                levelTwoBean.setId(levelTwo.getId());
                levelTwoBean.setLabel(levelTwo.getLabel());
                levelTwoBean.setTitle(levelTwo.getTitle());
                levelTwoBean.setMany(levelTwo.isMany());
                if (levelTwo.getUrl() != null) {

                    Material url = levelTwo.getUrl();
                    levelTwoBean.setMediaType(url.getType());
                    MaterialBean urlMaterial = new MaterialBean();
                    urlMaterial.setId(url.getId());
                    urlMaterial.setMd5Name(url.getMacName());
                    urlMaterial.setOriginName(url.getOriginName());
                    levelTwoBean.setUrlMaterial(urlMaterial);
                }

                TreeLevel treeLevelTwo = new TreeLevel();
                treeLevelTwo.setText(levelTwo.getLabel());
                treeLevelTwo.setType(1);
                treeLevelTwo.setData(levelTwoBean);
                List<Content> contents = levelTwo.getContents();
                List<TreeLevel> treeLevelContents = new ArrayList<>();
                if (contents.size() > 0) {
                    for (Content content : contents) {
                        TreeLevel treeLevelContent = new TreeLevel();
                        ContentBean contentBean = new ContentBean();
                        contentBean.setId(content.getId());
                        contentBean.setMediaType(content.getType());
                        contentBean.setLabel(content.getLabel());
                        contentBean.setTitle(contentBean.getTitle());
                        List<Material> materials = content.getMaterials();
                        List<MaterialBean> materialBeans = new ArrayList<>();
                        for (Material material : materials) {
                            MaterialBean mb = new MaterialBean();
                            mb.setId(material.getId());
                            mb.setMd5Name(material.getMacName());
                            mb.setOriginName(material.getOriginName());
                            materialBeans.add(mb);
                        }
                        contentBean.setMaterials(materialBeans);
                        String title = content.getTitle() != null ? content.getTitle() : "content";
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

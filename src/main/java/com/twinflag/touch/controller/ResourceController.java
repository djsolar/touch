package com.twinflag.touch.controller;

import com.twinflag.touch.config.Config;
import com.twinflag.touch.entity.FileMeta;
import com.twinflag.touch.model.Achieve;
import com.twinflag.touch.model.Material;
import com.twinflag.touch.service.AchieveService;
import com.twinflag.touch.service.MaterialService;
import com.twinflag.touch.utils.FileUtil;
import com.twinflag.touch.utils.MD5Util;
import com.twinflag.touch.utils.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    private LinkedList<FileMeta> files = new LinkedList<>();

    @Autowired
    private AchieveService achieveService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private Config config;

    private FileMeta fileMeta = null;

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

    @RequestMapping(value = "/getAchieveMaterial", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Material> getAchieveMaterial(Integer id) {
        Achieve achieve = achieveService.findAchieve(id);
        List<Material> materials = achieve.getMaterials();

        return materials;
    }

    @RequestMapping(value = "/deleteAchieve", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteAchieve(Integer id) {
        return achieveService.deleteAchieve(id);
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public LinkedList<FileMeta> uploadResource(MultipartHttpServletRequest request, Integer achieveId) {
        System.out.println("achieveId = " + achieveId);

        Achieve achieve = achieveService.findAchieve(achieveId);
        //1. build an iterator
        Iterator<String> itr =  request.getFileNames();
        MultipartFile mpf;
        List<Material> materials = new ArrayList<>();
        Material material;
        //2. get each file
        while(itr.hasNext()){
            //2.1 get next MultipartFile
            mpf = request.getFile(itr.next());
            System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());

            //2.2 if files > 10 remove the first from the list
            if(files.size() >= 10)
                files.pop();

            //2.3 create new fileMeta
            String fileName = mpf.getOriginalFilename();
            fileMeta = new FileMeta();
            fileMeta.setFileName(fileName);
            fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
            String fileType;
            int type = FileUtil.getType(fileName);
            if (type == SourceType.IMAGE.getType()) {
                fileType = "图片";
            } else {
                fileType = "文档";
            }
            String filePath = config.getUploadMaterialPath() + mpf.getOriginalFilename();
            fileMeta.setFileType(fileType);
            material = new Material();
            material.setAchieve(achieve);
            material.setOriginName(fileName);
            material.setType(type);
            try {
                fileMeta.setBytes(mpf.getBytes());
                String md5Name = MD5Util.getMd5ByFile(mpf.getBytes());
                String md5Path = FileUtil.getMd5Path(filePath, md5Name);
                FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(md5Path));
                File file = new File(md5Path);
                String macName = file.getName();
                material.setMacName(macName);
                material.setPath(md5Path);

            } catch (IOException e) {
                e.printStackTrace();
            }
            //2.4 add to files
            files.add(fileMeta);
            materials.add(material);
        }
        materialService.saveMaterials(materials);
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        System.out.println("size = " + files.size());
        return files;
    }
}

package com.twinflag.touch.service;

import com.twinflag.touch.config.Config;
import com.twinflag.touch.entity.ContentBean;
import com.twinflag.touch.entity.DataTableViewPage;
import com.twinflag.touch.entity.LevelOneBean;
import com.twinflag.touch.entity.LevelTwoBean;
import com.twinflag.touch.model.*;
import com.twinflag.touch.respository.ProgramRepository;
import com.twinflag.touch.respository.UserRepository;
import com.twinflag.touch.utils.*;
import org.apache.commons.io.FileUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private Config config;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private AchieveService achieveService;

    @Autowired
    private MaterialService materialService;

    @Override
    public void saveTemplate(Program program) {

    }

    @Override
    public void uploadTemplate(MultipartFile file, String fileName) throws Exception {
        String uploadFilePath = config.getUploadTemplatePath();
        System.out.println(uploadFilePath);
        FileUtil.uploadFile(file.getBytes(), uploadFilePath, fileName);
        String srcPath = uploadFilePath + fileName;
        ZipUtils.unZip(srcPath, uploadFilePath);
        Program program = transferEntityToModal(fileName, uploadFilePath);
        programRepository.save(program);
    }

    @Override
    public DataTableViewPage<Program> findAllTemplate(int page, int pageSize) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Specification<Program> programSpecification = new Specification<Program>() {
            @Override
            public Predicate toPredicate(Root<Program> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = cb.equal(root.get("createUser").as(User.class), user);
                Predicate p2 = cb.equal(root.get("type").as(Integer.class), ProgramType.TEMPLATE.getType());
                return cb.and(p1, p2);
            }
        };
        Page<Program> programPage = programRepository.findAll(programSpecification, PageRequestBuilder.buildPageRequest(page, pageSize));
        DataTableViewPage<Program> programDataTableViewPage = new DataTableViewPage<>();
        programDataTableViewPage.setAaData(programPage.getContent());
        programDataTableViewPage.setRecordsFiltered(programPage.getTotalElements());
        programDataTableViewPage.setRecordsTotal(programPage.getTotalElements());
        return programDataTableViewPage;
    }

    @Override
    public boolean deleteTemplate(Integer id) {
        programRepository.delete(id);
        return false;
    }



    @Override
    public List<Object> getTemplateInfos() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        return programRepository.findTemplateInfo(ProgramType.TEMPLATE.getType(), user);
    }

    public Program findTemplate(Integer id) {
        return programRepository.findOne(id);
    }


    private Program transferEntityToModal(String fileName, String filePath) throws DocumentException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Achieve achieve = achieveService.findAchieveByNameAndCreateUserAndAuthority("其他", user, 0);
        if (achieve == null) {
            achieve = new Achieve();
            achieve.setCreateUser(user);
            achieve.setName("其他");
            achieve.setAuthority(0);
            achieve.setCreateDate(new Date());
            achieve.setMaterials(new HashSet<>());
        }

        String srcPath = filePath + fileName;
        String directoryName = fileName.replace(".zip", "");
        String unZipPath = filePath + directoryName;
        String menuPath = unZipPath + "/" + Constant.MENU_FILE_NAME;
        ParseUtils parseUtils = new ParseUtils();
        List<LevelOneBean> levelOneBeans = parseUtils.parseMenu(menuPath);
        Program program = new Program();
        program.setType(ProgramType.TEMPLATE.getType());
        program.setProgramName(directoryName);
        program.setSourcePath(unZipPath);
        program.setZipPath(srcPath);
        program.setCreateUser(user);
        program.setUpdateUser(user);
        List<LevelOne> levelOnes = new ArrayList<>();
        for (LevelOneBean lob : levelOneBeans) {
            LevelOne levelOne = new LevelOne();
            String normalPicPath = unZipPath + "/" + lob.getNormalPic();
            Material normalPic = transferMaterial(normalPicPath);
            achieve.getMaterials().add(normalPic);
            levelOne.setNormalPic(normalPic);
            String selectedPicPath = unZipPath + "/" + lob.getSelectedPic();
            Material selectedPic = transferMaterial(selectedPicPath);
            achieve.getMaterials().add(selectedPic);
            levelOne.setSelectedPic(selectedPic);
            levelOne.setUrl(lob.getUrl());
            levelOne.setProgram(program);

            List<LevelTwoBean> levelTwoBeans = lob.getTwoContent();
            List<LevelTwo> levelTwos = new ArrayList<>();
            for (LevelTwoBean ltb : levelTwoBeans) {
                LevelTwo levelTwo = new LevelTwo();
                levelTwo.setLabel(ltb.getLabel());
                levelTwo.setTitle(ltb.getTitle());
                levelTwo.setMany(ltb.isMany());
                if (!StringUtils.isEmpty(ltb.getUrl())) {
                    String urlPath = unZipPath + "/" +ltb.getUrl();
                    Material urlMaterial = transferMaterial(urlPath);
                    achieve.getMaterials().add(urlMaterial);
                    levelTwo.setUrl(urlMaterial);
                }
                levelTwo.setLevelOne(levelOne);
                List<ContentBean> contentBeans = ltb.getContentBeans();
                List<Content> contents = new ArrayList<>();
                if (contentBeans != null && contentBeans.size() > 0) {
                    for (ContentBean contentBean : contentBeans) {
                        Content content = new Content();
                        content.setType(contentBean.getMediaType());
                        content.setLabel(contentBean.getLabel());
                        content.setTitle(contentBean.getTitle());
                        content.setLevelTwo(levelTwo);
                        List<String> paths = contentBean.getPaths();
                        List<Material> materials = new ArrayList<>();
                        for (String path : paths) {
                            String materialPath = unZipPath + "/" + path;
                            Material material = transferMaterial(materialPath);
                            materials.add(material);
                        }
                        content.setMaterials(materials);
                        achieve.getMaterials().addAll(materials);
                        contents.add(content);
                    }
                }
                levelTwo.setContents(contents);
                levelTwos.add(levelTwo);
            }
            levelOne.setLevelTwos(levelTwos);

            levelOnes.add(levelOne);
        }
        program.setLevelOnes(levelOnes);
        achieveService.saveAchieve(achieve);
        return program;
    }

    private Material transferMaterial(String filePath) {
        File file = new File(filePath);
        System.out.println("file.exist() = " + file.exists());
        String originName = file.getName();
        String md5;
        try {
            md5 = MD5Util.getMd5ByFile(file);
            String suffix = originName.substring(originName.lastIndexOf('.'));
            String md5Name = md5 + suffix;
            String path = config.getUploadMaterialPath() + md5Name;
            Material material = materialService.findMaterialByMacName(md5Name);
            if (material == null) {
                int type = FileUtil.getType(originName);
                material = new Material();
                material.setMacName(md5Name);
                material.setOriginName(originName);
                material.setPath(path);
                material.setType(type);
            }
            File destFile = new File(path);
            if (!destFile.exists()) {
                try {
                    FileUtils.copyFile(file, destFile);
                    System.out.println("复制文件成功");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.printf("复制文件失败");
                }
            }

            return material;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\uploadTemplate\\StreamingAssets\\icons 2\\信访服务选中.png");
        String originName = file.getName();
        String suffix = originName.substring(originName.lastIndexOf('.'));
        String md5 = MD5Util.getMd5ByFile(file);
        String md5Name = md5 + suffix;
        String destPath = "D:\\uploadMatrial\\" + md5Name;
        FileUtils.copyFile(file, new File(destPath));

    }
}

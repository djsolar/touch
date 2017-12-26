package com.twinflag.touch.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinflag.touch.config.Config;
import com.twinflag.touch.entity.DataTableViewPage;
import com.twinflag.touch.model.*;
import com.twinflag.touch.respository.MaterialRepository;
import com.twinflag.touch.respository.ProgramRepository;
import com.twinflag.touch.respository.UserRepository;
import com.twinflag.touch.tree.TreeLevel;
import com.twinflag.touch.utils.PageRequestBuilder;
import com.twinflag.touch.utils.ProgramType;
import com.twinflag.touch.utils.ProgramZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private Config config;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private LevelOneService levelOneService;

    @Override
    public void saveProgram(String programName, String programContent) throws IOException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        ObjectMapper om = new ObjectMapper();
        JavaType javaType = getCollectionType(om, ArrayList.class, TreeLevel.class);
        List<TreeLevel> treeLevels = om.readValue(programContent, javaType);
        System.out.println(treeLevels.size());
        Program program = transferTreeLevel2Program(user, programName, treeLevels);
        programRepository.save(program);
    }

    private JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    private Program transferTreeLevel2Program(User user, String programName, List<TreeLevel> treeLevels) throws IOException {
        if (treeLevels == null || treeLevels.size() == 0)
            return null;
        Program program = new Program();
        program.setProgramName(programName);
        program.setType(ProgramType.PROGRAM.getType());
        program.setCreateTime(new Date());
        program.setUpdateTime(new Date());
        program.setUpdateUser(user);
        program.setCreateUser(user);
        List<LevelOne> levelOnes = new ArrayList<>();
        for(TreeLevel treeLevel: treeLevels) {
            LevelOne levelOne = new LevelOne();
            LinkedHashMap<String, Object> levelOneData = (LinkedHashMap<String, Object>) treeLevel.getData();
            LinkedHashMap<String, Object> normalPic = (LinkedHashMap<String, Object>) levelOneData.get("normalMaterial");
            Material normalMaterial = materialRepository.findOne((Integer) normalPic.get("id"));
            LinkedHashMap<String, Object> selectedPic = (LinkedHashMap<String, Object>) levelOneData.get("selectedMaterial");
            Material selectedMaterial = materialRepository.findOne((Integer) selectedPic.get("id"));
            levelOne.setNormalPic(normalMaterial);
            levelOne.setSelectedPic(selectedMaterial);
            List<TreeLevel> treeLevelTwos = treeLevel.getNodes();
            List<LevelTwo> levelTwos = new ArrayList<>();
            for(TreeLevel treeLevelTwo : treeLevelTwos) {
                LevelTwo levelTwo = new LevelTwo();
                LinkedHashMap<String, Object> levelTwoData = (LinkedHashMap<String, Object>) treeLevelTwo.getData();
                levelTwo.setLabel((String) levelTwoData.get("label"));
                levelTwo.setTitle((String) levelTwoData.get("title"));
                levelTwo.setMany((Boolean) levelTwoData.get("many"));
                if (levelTwoData.get("urlMaterial") != null) {
                    LinkedHashMap<String, Object> url = (LinkedHashMap<String, Object>) levelTwoData.get("urlMaterial");
                    System.out.println(url.get("id"));
                    Material urlMaterial = materialRepository.findOne((Integer) url.get("id"));
                    levelTwo.setUrl(urlMaterial);
                    levelTwo.setLevelOne(levelOne);
                } else {
                    List<TreeLevel> treeLevelContents = treeLevelTwo.getNodes();
                    List<Content> contents = new ArrayList<>();
                    if (treeLevelContents != null && treeLevelContents.size() > 0) {
                        for(TreeLevel treeLevelContent : treeLevelContents) {
                            Content content = new Content();
                            LinkedHashMap<String, Object> contentData = (LinkedHashMap<String, Object>) treeLevelContent.getData();
                            content.setLabel((String) contentData.get("label"));
                            content.setTitle((String) contentData.get("title"));
                            if (contentData.get("title") == null) {
                                content.setTitle(content.getLabel());
                            }
                            content.setType((Integer) contentData.get("mediaType"));
                            ArrayList<LinkedHashMap<String, Object>> materialList = (ArrayList<LinkedHashMap<String, Object>>) contentData.get("materials");
                            List<Material> materials = new ArrayList<>();
                            if (materialList != null && materialList.size() > 0) {
                                for(LinkedHashMap<String, Object> ml : materialList) {
                                    Material material = materialRepository.findOne((Integer) ml.get("id"));
                                    materials.add(material);
                                }
                            }
                            content.setMaterials(materials);
                            content.setLevelTwo(levelTwo);
                            contents.add(content);
                        }
                    }
                    levelTwo.setContents(contents);
                    levelTwo.setLevelOne(levelOne);
                }
                levelTwos.add(levelTwo);
            }
            levelOne.setLevelTwos(levelTwos);
            levelOne.setProgram(program);
            levelOnes.add(levelOne);
        }
        ProgramZipUtils programZipUtils = new ProgramZipUtils(levelOnes, programName, config);
        programZipUtils.zipProgram();
        program.setLevelOnes(levelOnes);
        program.setZipPath("/programZip/" + programName + ".zip");
        return program;
    }

    @Override
    public void deleteProgram(Integer id) {
        programRepository.delete(id);
    }

    @Override
    public void updateProgram(Integer id, String programContent) throws IOException {
        ObjectMapper om = new ObjectMapper();
        JavaType javaType = getCollectionType(om, ArrayList.class, TreeLevel.class);
        List<TreeLevel> treeLevels = om.readValue(programContent, javaType);
        Program program = programRepository.findOne(id);
        for(LevelOne levelOne : program.getLevelOnes()) {
            levelOneService.deleteLevelOne(levelOne);
        }
        Program tempProgram = transferTreeLevel2Program(program.getCreateUser(), program.getProgramName(), treeLevels);
        for(LevelOne levelOne : tempProgram.getLevelOnes()) {
            levelOne.setProgram(program);
        }
        program.setUpdateTime(new Date());
        program.setLevelOnes(tempProgram.getLevelOnes());
        programRepository.save(program);
    }


    @Override
    public void findAllProgram() {

    }

    @Override
    public void publishProgram(Program program) {

    }

    @Override
    public void stopProgram(Program program) {

    }

    @Override
    public Program findProgram(Integer id) {
        return programRepository.findOne(id);
    }

    @Override
    public boolean isProgramNameExist(String programName) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Specification<Program> programSpecification = new Specification<Program>() {
            @Override
            public Predicate toPredicate(Root<Program> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = cb.equal(root.get("createUser").as(User.class), user);
                Predicate p2 = cb.equal(root.get("programName").as(String.class), programName);
                return cb.and(p1, p2);
            }
        };
        Program program = programRepository.findOne(programSpecification);
        return program != null;
    }

    @Override
    public DataTableViewPage<Program> findAll(int page, int pageSize) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());
        Specification<Program> programSpecification = new Specification<Program>() {
            @Override
            public Predicate toPredicate(Root<Program> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = cb.equal(root.get("createUser").as(User.class), user);
                Predicate p2 = cb.equal(root.get("type").as(Integer.class), ProgramType.PROGRAM.getType());
                return cb.and(p1, p2);
            }
        };

        Page<Program> programPage = programRepository.findAll(programSpecification, PageRequestBuilder.buildPageRequest(page, pageSize));
        DataTableViewPage<Program> programDataTableViewPage = new DataTableViewPage<>();
        programDataTableViewPage.setAaData(programPage.getContent());
        programDataTableViewPage.setRecordsTotal(programPage.getTotalElements());
        programDataTableViewPage.setRecordsFiltered((int) programPage.getTotalElements());
        return programDataTableViewPage;
    }
}

package com.twinflag.touch.repository;


import com.twinflag.touch.model.LevelOne;
import com.twinflag.touch.model.Program;
import com.twinflag.touch.model.User;
import com.twinflag.touch.respository.LevelOneRepository;
import com.twinflag.touch.respository.ProgramRepository;
import com.twinflag.touch.respository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProgramRepository {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LevelOneRepository levelOneRepository;

    @Test
    public void testFindAllTemplate() {
        User user = userRepository.findByUsername("admin");
        List<Program> programList = programRepository.findProgramsByTypeAndCreateUser(1, user);
        System.out.println(programList.size());
    }

    @Test
    public void testUpdateProgram() {
        Program program = programRepository.findOne(17);
        Program tempProgram = new Program();
        tempProgram.setId(program.getId());
        tempProgram.setLevelOnes(program.getLevelOnes());
        tempProgram.setZipPath(program.getZipPath());
        tempProgram.setUpdateTime(new Date());
        tempProgram.setUpdateUser(program.getUpdateUser());
        tempProgram.setCreateUser(program.getCreateUser());
        tempProgram.setType(program.getType());
        programRepository.save(tempProgram);
    }

    @Test
    public void testDeleteLevelOne() {
        Program program = programRepository.findOne(17);
        LevelOne levelOne = program.getLevelOnes().remove(0);
        levelOne.setProgram(null);
        levelOneRepository.save(levelOne);
        levelOneRepository.delete(levelOne);
    }
}

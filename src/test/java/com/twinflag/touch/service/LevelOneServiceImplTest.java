package com.twinflag.touch.service;

import com.twinflag.touch.model.LevelOne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LevelOneServiceImplTest {

    @Autowired
    private LevelOneService levelOneService;

    @Test
    public void deleteLevelOne() {

    }

    @Test
    public void deleteLevelOne1() {
        LevelOne levelOne = levelOneService.findLevelOne(296);
        levelOne.setProgram(null);
        levelOneService.saveLevelOne(levelOne);
        levelOneService.deleteLevelOne(levelOne);
    }
}
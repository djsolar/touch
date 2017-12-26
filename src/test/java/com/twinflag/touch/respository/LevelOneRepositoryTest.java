package com.twinflag.touch.respository;

import com.twinflag.touch.model.LevelTwo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LevelOneRepositoryTest {

    @Autowired
    private LevelTwoRepository levelTwoRepository;

    @Test
    public void deleteLevelTwo() {
        LevelTwo levelTwo = levelTwoRepository.findOne(507);
        levelTwo.getContents();
        levelTwoRepository.save(levelTwo);
        levelTwoRepository.delete(levelTwo);
    }
}
package com.twinflag.touch.service;

import com.twinflag.touch.model.Achieve;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class AchieveServiceImplTest {

    @Autowired
    private AchieveService achieveService;

    @Test
    public void saveAchieve() {
        Achieve achieve = new Achieve();
        achieve.setName("aa");
        achieve.setAuthority(0);
        achieve.setCreateDate(new Date());
        Achieve achieve1 = achieveService.saveAchieve(achieve);
        System.out.println(achieve1.getId());
    }
}
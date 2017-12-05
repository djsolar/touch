package com.twinflag.touch.repository;


import com.twinflag.touch.model.Program;
import com.twinflag.touch.model.User;
import com.twinflag.touch.respository.ProgramRepository;
import com.twinflag.touch.respository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProgramRepository {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAllTemplate() {
        User user = userRepository.findByUsername("admin");
        List<Program> programList = programRepository.findProgramsByTypeAndCreateUser(1, user);
        System.out.println(programList.size());
    }
}

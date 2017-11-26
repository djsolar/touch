package com.twinflag.touch.service;

import com.twinflag.touch.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setEmail("719151975@qq.com");
        userService.save(user);
    }
}

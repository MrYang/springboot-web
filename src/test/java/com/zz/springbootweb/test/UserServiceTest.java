package com.zz.springbootweb.test;

import com.zz.springbootweb.Application;
import com.zz.springbootweb.entity.User;
import com.zz.springbootweb.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test_createUser(){
        User user = new User();
        user.setUsername("yxb");
        user.setPlainPassword("111111");
        user.setCreationTime(new Date());

        userService.createUser(user);
    }
}

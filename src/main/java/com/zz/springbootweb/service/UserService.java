package com.zz.springbootweb.service;

import com.zz.springbootweb.entity.User;
import com.zz.springbootweb.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, String> {

    @Autowired
    private UserDao userDao;

    public void createUser(User user) {
        userDao.save(user);
    }

}

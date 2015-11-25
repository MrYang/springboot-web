package com.zz.springbootweb.service;

import com.zz.springbootweb.entity.User;
import com.zz.springbootweb.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends BaseService<User, Long> {

    @Autowired
    private UserDao userDao;

    public void createUser(User user) {
        user.setStatus("enable");
        userDao.save(user);
    }

}

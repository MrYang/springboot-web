package com.zz.springbootweb.service;

import com.zz.springbootweb.entity.User;
import com.zz.springbootweb.repository.UserDao;
import com.zz.springbootweb.utils.Digests;
import org.apache.commons.codec.binary.Hex;
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
        entryptPassword(user);
        userDao.save(user);
    }

    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(8);
        user.setSalt(Hex.encodeHexString(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, 1024);
        user.setPassword(Hex.encodeHexString(hashPassword));
    }

}

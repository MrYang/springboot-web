package com.zz.springbootweb.repository;

import com.zz.springbootweb.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User, Long> {
}

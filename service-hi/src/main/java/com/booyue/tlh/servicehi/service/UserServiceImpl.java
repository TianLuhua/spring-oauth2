package com.booyue.tlh.servicehi.service;

import com.booyue.tlh.servicehi.dao.User;
import com.booyue.tlh.servicehi.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Override
    public User create(String userName, String password) {
        String hash = bCryptPasswordEncoder.encode(password);
        User user = new User(userName, hash);
        return userDao.save(user);
    }
}

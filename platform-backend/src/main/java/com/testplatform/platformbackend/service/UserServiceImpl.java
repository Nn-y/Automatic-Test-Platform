package com.testplatform.platformbackend.service;
import com.testplatform.platformbackend.dao.UserDao;
import com.testplatform.platformbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int checkUser(User user){
        return userDao.checkUser(user);
    }

    @Override
    public int isExist(String name) {
        return userDao.isExist(name);
    }
}

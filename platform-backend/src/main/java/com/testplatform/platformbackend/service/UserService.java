package com.testplatform.platformbackend.service;

import com.testplatform.platformbackend.entity.User;

public interface UserService {
    int addUser(User user);
    int checkUser(User user);
    int isExist(String name);
}

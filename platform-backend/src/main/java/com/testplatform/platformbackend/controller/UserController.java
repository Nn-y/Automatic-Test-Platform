package com.testplatform.platformbackend.controller;

import com.testplatform.platformbackend.entity.User;
import com.testplatform.platformbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/register/isexist")
    public int isExist(@RequestParam String name){
        System.out.println();
        return userService.isExist(name);
    }

    @ResponseBody
    @RequestMapping("/register")
    public int addUser(@RequestParam Map<String,String> map){
//        System.out.println(user.getName());
        User user = new User();
        user.setName(map.get("name"));
        user.setPassword(map.get("pswd"));
        return userService.addUser(user);
    }

    @ResponseBody
    @PostMapping("/login")
    public int checkUser(@RequestBody Map<String,String> map){
        User user = new User();
        user.setName(map.get("name"));
        user.setPassword(map.get("pswd"));
        if(isExist(user.getName()) == 1){
            if(userService.checkUser(user)==1){
                return 1;
            }else {
                return 0;
            }
        }else {
            return 0;
        }

    }
}

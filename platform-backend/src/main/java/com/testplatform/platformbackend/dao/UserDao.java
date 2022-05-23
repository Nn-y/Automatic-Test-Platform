package com.testplatform.platformbackend.dao;

import com.testplatform.platformbackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int isExist(String name){
        String sql = "select count(name) from users where name = ?";//1为存在，0为不存在
        return jdbcTemplate.queryForObject(sql,Integer.class,name);
    }

    public int addUser(User user){
        String sql = "insert into users(name,pswd) values(?,md5(?))";
        return jdbcTemplate.update(sql,user.getName(),user.getPassword());
    }

    public int checkUser(User user){
        String sql1 = "select count(name) from users where name = ? and pswd = md5(?)";
        int flag = jdbcTemplate.queryForObject(sql1,Integer.class,user.getName(),user.getPassword());
        if(flag == 1){
            return 1;//用户验证成功
        }else {
            return 0;//验证失败
        }
    }
}

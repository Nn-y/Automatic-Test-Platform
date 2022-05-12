package com.testplatform.platformbackend.dao;

import com.testplatform.platformbackend.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ProjectDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Project> getProjects(){
        String sql = "select * from project";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Project.class));
    }
}

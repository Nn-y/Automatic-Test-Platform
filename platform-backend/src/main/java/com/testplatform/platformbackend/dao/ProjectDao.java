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

    public List<Project> getProjects(String user){
        String sql = "select * from project where user = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Project.class),user);
    }

    public String getProject(int projectId){
        String sql = "select name from project where id = ?";
        return jdbcTemplate.queryForObject(sql,String.class,projectId);
    }

    public int addProject(String user){
        String sql = "insert into project(name,user) values('未命名',?)";
        jdbcTemplate.update(sql,user);
        String sql2 = "select max(id) from project";
        int projectId = jdbcTemplate.queryForObject(sql2,Integer.class);
        String sql3 = "insert into function_tree(pid,label,project) values(0,'全部用例',?)";
        jdbcTemplate.update(sql3,projectId);
        String sql4 = "insert into interface_tree(pid,label,project) values(0,'全部用例',?)";
        jdbcTemplate.update(sql4,projectId);
        String sql5 = "insert into if_tree(pid,label,project) values(0,'全部接口',?)";
        return jdbcTemplate.update(sql5,projectId);
    }

    public int delProject(int id){
        String sql = "delete from project where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    public void updateProject(List<Project> projects){
        String sql = "update project set name = ? where id = ?";
        for(Project project:projects){
            jdbcTemplate.update(sql,project.getName(),project.getId());
        }
    }
}

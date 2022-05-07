package com.testplatform.platformbackend.dao;

import com.fasterxml.jackson.databind.BeanProperty;
import com.testplatform.platformbackend.entity.FunctionTcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionTcInfoDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public FunctionTcInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FunctionTcInfo> getInfo(){
        String sql = "select * from function_tc_information";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(FunctionTcInfo.class));
    }

    public FunctionTcInfo getOneInfo(int id){
        String sql = "select * from function_tc_information where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(FunctionTcInfo.class),id);
    }

    public void addDefault(int nodeId){
        String sql1 = "insert into function_tc_information(name,user,status,category) values ('未创建','无','未执行',?)";
        String sql2 = "select id from function_tc_information where ctime = (select max(ctime) from function_tc_information )";
        jdbcTemplate.update(sql1,nodeId);
        int index = jdbcTemplate.queryForObject(sql2,Integer.class);
//        System.out.println(index);
        String sql3 = "insert into function_tc_pre(id,pre) values(?,'无')";
        jdbcTemplate.update(sql3,index);
        String sql4 = "insert into function_tc_detial(id,steps,expected,actual) values(?,'无','无','无')";
        jdbcTemplate.update(sql4,index);
    }

    public int updateInfo(FunctionTcInfo info){
        String sql = "UPDATE function_tc_information SET name=?,version=?,tag=?,module=?,bugs=?,user=?," +
                "result=?,level=?,status=? where id=?";
        return jdbcTemplate.update(sql,info.getName(),info.getVersion(),info.getTag(),info.getModule(),info.getBugs(),
                info.getUser(),info.getResult(),info.getLevel(),info.getStatus(),info.getId());
    }

    public int deleteInfo(int id){
        String sql = "delete from function_tc_information where id=?";
        String sql2 = "delete from function_tc_pre where id = ?";
        jdbcTemplate.update(sql2,id);
        return jdbcTemplate.update(sql,id);
    }


}

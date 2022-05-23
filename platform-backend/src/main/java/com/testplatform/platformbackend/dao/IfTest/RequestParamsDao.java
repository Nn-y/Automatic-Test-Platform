package com.testplatform.platformbackend.dao.IfTest;

import com.testplatform.platformbackend.entity.RequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestParamsDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public RequestParamsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RequestParams> getParams(int id){
        String sql = "select * from request_params where id = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(RequestParams.class),id);
    }

    public void updateParams(RequestParams params){
        String sql = "update request_params set `key` = ?,`value` = ?,describes = ? where id = ? and xid = ?";
        jdbcTemplate.update(sql,params.getKey(),params.getValue(), params.getDescribes(),params.getId(),params.getXid());
    }

    public int addRow(int id){
        String sql = "insert into request_params(id,`key`,`value`,describes) values(?,'无','无',null);";
        return jdbcTemplate.update(sql,id);
    }

    public int deleteRow(int xid){
        String sql = "delete from request_params where xid = ?";
        return jdbcTemplate.update(sql,xid);
    }
}

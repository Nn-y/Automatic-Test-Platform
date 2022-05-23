package com.testplatform.platformbackend.dao.IfTest;

import com.testplatform.platformbackend.entity.MyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JmeterDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public JmeterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MyRequest getRequest(int id){
        String sql ="select request,domain,port,path from if_list_detial where id = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<MyRequest>() {
            @Override
            public MyRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
                MyRequest myRequest = new MyRequest();
                myRequest.setMethod(rs.getString("request"));
                myRequest.setPath(rs.getString("path"));
                myRequest.setDomain(rs.getString("domain"));
                myRequest.setPort(rs.getString("port"));
                return myRequest;
            }
        },id);
    }
}

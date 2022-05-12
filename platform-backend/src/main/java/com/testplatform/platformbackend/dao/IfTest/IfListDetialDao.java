package com.testplatform.platformbackend.dao.IfTest;

import com.testplatform.platformbackend.entity.IfListDetial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IfListDetialDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public IfListDetialDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public IfListDetial getDetial(int id){
        String sql = "select * from if_list_detial where id = "+id;
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(IfListDetial.class));
    }

    public int save(IfListDetial detial){
        String sql = "update if_list_detial set name = ?,request = ?,url = ?,user = ?," +
                "module = ?,status = ?,tag = ?,describes = ? where id = ?";
        return jdbcTemplate.update(sql,detial.getName(),detial.getRequest(),detial.getUrl(),detial.getUser(),
                detial.getModule(),detial.getStatus(),detial.getTag(),detial.getDescribes(),detial.getId());
    }
}

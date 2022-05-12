package com.testplatform.platformbackend.dao.IfTest;

import com.testplatform.platformbackend.entity.IfListDetial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IfListDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public IfListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addDefault(int nodeId){
        String sql1 = "insert into if_list(name,user,status,category) values ('未创建','无','未执行',?)";
        String sql2 = "select id from if_list where ctime = (select max(ctime) from if_list )";
        jdbcTemplate.update(sql1,nodeId);
        int index = jdbcTemplate.queryForObject(sql2,Integer.class);
//        System.out.println(index);
        String sql3 = "insert into if_list_detial(id,name,user,status) values(?,'未创建','无','未执行')";
//        String sql3 = "insert into function_tc_pre(id,pre) values(?,'无')";
        jdbcTemplate.update(sql3,index);
//        String sql4 = "insert into function_tc_detial(id,steps,expected,actual) values(?,'无','无','无')";
//        jdbcTemplate.update(sql4,index);
    }

    public int deleteInfo(int id){
        String sql = "delete from if_list where id=?";
        return jdbcTemplate.update(sql,id);
    }

    public int updateInfo(IfListDetial detial){
        String sql = "update if_list set name = ?,type = ?,user = ?,tag = ?,status = ? where id = ?";
        return jdbcTemplate.update(sql,detial.getName(),detial.getRequest(),detial.getUser(),
                detial.getTag(),detial.getStatus(),detial.getId());
    }
}

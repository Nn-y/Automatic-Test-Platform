package com.testplatform.platformbackend.dao.IfTc;

import com.testplatform.platformbackend.entity.FuncTcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IfTcInfoDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public IfTcInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FuncTcInfo> getInfo(){
        String sql = "select * from interface_tc_info ";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(FuncTcInfo.class));
    }

    public FuncTcInfo getOneInfo(int id){
        String sql = "select * from interface_tc_info  where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(FuncTcInfo.class),id);
    }

    public void addDefault(int nodeId){
        String sql1 = "insert into interface_tc_info (name,user,status,category) values ('未创建','无','未执行',?)";
        String sql2 = "select id from interface_tc_info  where ctime = (select max(ctime) from interface_tc_info  )";
        jdbcTemplate.update(sql1,nodeId);
        int index = jdbcTemplate.queryForObject(sql2,Integer.class);
//        System.out.println(index);
        String sql3 = "insert into interface_tc_pre(id,pre) values(?,'无')";
        jdbcTemplate.update(sql3,index);
        String sql4 = "insert into interface_tc_detial(id,steps,expected,actual) values(?,'无','无','无')";
        jdbcTemplate.update(sql4,index);
    }

    public int updateInfo(FuncTcInfo info){
        String sql = "UPDATE interface_tc_info SET name=?,version=?,tag=?,module=?,bugs=?,user=?," +
                "result=?,level=?,status=? where id=?";
        return jdbcTemplate.update(sql,info.getName(),info.getVersion(),info.getTag(),info.getModule(),info.getBugs(),
                info.getUser(),info.getResult(),info.getLevel(),info.getStatus(),info.getId());
    }

    public int deleteInfo(int id){
        String sql = "delete from interface_tc_info where id=?";
        String sql2 = "delete from interface_tc_pre where id = ?";
        jdbcTemplate.update(sql2,id);
        return jdbcTemplate.update(sql,id);
    }
}

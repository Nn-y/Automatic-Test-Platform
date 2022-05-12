package com.testplatform.platformbackend.dao.IfTc;

import com.testplatform.platformbackend.entity.FuncTcDetial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class IfTcDetialDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public IfTcDetialDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<FuncTcDetial> getDetial(int id){
        String sql = "select * from interface_tc_detial where id = "+id;
        return jdbcTemplate.query(sql, new RowMapper<FuncTcDetial>() {
            @Override
            public FuncTcDetial mapRow(ResultSet rs, int rowNum) throws SQLException {
                FuncTcDetial funcTcDetial = new FuncTcDetial();
                funcTcDetial.setId(rs.getInt("id"));
                funcTcDetial.setXid(rs.getInt("xid"));
                funcTcDetial.setSteps(rs.getString("steps"));
                funcTcDetial.setExpected(rs.getString("expected"));
                funcTcDetial.setActual(rs.getString("actual"));
                String pre = getPre(id);
                funcTcDetial.setPre(pre);
                return funcTcDetial;
            }
        });
    }

    public String getPre(int id){
        String sql = "select * from interface_tc_pre where id ="+id;
//        System.out.println(((FuncTcDetial)jdbcTemplate.query(sql, new BeanPropertyRowMapper(FuncTcDetial.class)).get(0)).getPre());
        return ((FuncTcDetial)jdbcTemplate.query(sql, new BeanPropertyRowMapper(FuncTcDetial.class)).get(0)).getPre();
    }

    public int addRow(int id){
        String sql = "insert into interface_tc_detial(id,steps,expected,actual) values(?,'无','无','无');";
        return jdbcTemplate.update(sql,id);
    }

    public int deleteRow(int xid){
        String sql = "delete from interface_tc_detial where xid = ?";
        return jdbcTemplate.update(sql,xid);
    }

    public void updateDetial(List<FuncTcDetial> detials){
        String sql = "update interface_tc_detial set steps = ?,expected = ?,actual = ? where id = ? and xid = ?";
        for(FuncTcDetial detial :detials ) {
            jdbcTemplate.update(sql, detial.getSteps(), detial.getExpected(), detial.getActual(), detial.getId(), detial.getXid());
        }

    }
}

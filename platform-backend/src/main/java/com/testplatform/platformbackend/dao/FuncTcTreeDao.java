package com.testplatform.platformbackend.dao;

import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.entity.FunctionTcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public class FuncTcTreeDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public FuncTcTreeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FuncTcTree> getTree(){
        String sql = "select * from function_tree";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(FuncTcTree.class));
    }

    public int addTree(int pid){
        String sql = "insert into function_tree(pid,name) values(?,'无')";
        return jdbcTemplate.update(sql,pid);
    }

    public int deleteTree(int id){
        String sql = "delete from function_tree where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    public int updateTree(FuncTcTree tree){
        String sql = "update function_tree set label = ? where id = ?";
        return jdbcTemplate.update(sql,tree.getLabel(),tree.getId());
    }

    public List<FunctionTcInfo> nodeClick(int id){
        String sql = "select m.* from function_tc_information as m," +
                "(SELECT "+id +" as id UNION ALL select id from (select t1.id,if(find_in_set(pid, @pids) > 0," +
                " @pids := concat(@pids, ',', id), 0) as ischild from ( select id,pid from function_tree t) t1," +
                "(select @pids := "+id+") t2) t3 where ischild != \"0\") as n where m.category = n.id";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(FunctionTcInfo.class));
    }
}

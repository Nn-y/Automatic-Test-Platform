package com.testplatform.platformbackend.dao.FuncTc;

import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.entity.FuncTcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FuncTcTreeDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public FuncTcTreeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FuncTcTree> getTree(int projectId){
        String sql = "select * from function_tree where project = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(FuncTcTree.class),projectId);
    }

    public int addTree(int pid,int projectId){
        String sql = "insert into function_tree(pid,label,project) values(?,'无',?)";
        return jdbcTemplate.update(sql,pid,projectId);
    }

    public int deleteTree(int id){
        String sql = "delete from function_tree where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    public int updateTree(FuncTcTree tree){
        String sql = "update function_tree set label = ? where id = ?";
        return jdbcTemplate.update(sql,tree.getLabel(),tree.getId());
    }

    public List<FuncTcInfo> nodeClick(int id){
        String sql = "select m.* from function_tc_information as m," +
                "(SELECT "+id +" as id UNION ALL select id from (select t1.id,if(find_in_set(pid, @pids) > 0," +
                " @pids := concat(@pids, ',', id), 0) as ischild from ( select id,pid from function_tree t) t1," +
                "(select @pids := "+id+") t2) t3 where ischild != \"0\") as n where m.category = n.id";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(FuncTcInfo.class));
    }
}

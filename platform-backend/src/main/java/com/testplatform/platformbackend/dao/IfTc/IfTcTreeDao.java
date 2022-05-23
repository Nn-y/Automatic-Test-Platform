package com.testplatform.platformbackend.dao.IfTc;

import com.testplatform.platformbackend.entity.FuncTcInfo;
import com.testplatform.platformbackend.entity.FuncTcTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IfTcTreeDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public IfTcTreeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<FuncTcTree> getTree(int projectId){
        String sql = "select * from interface_tree where project = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(FuncTcTree.class),projectId);
    }

    public int addTree(int pid,int projectId){
        String sql = "insert into interface_tree(pid,label,project) values(?,'无',?)";
        return jdbcTemplate.update(sql,pid,projectId);
    }

    public int deleteTree(int id){
        String sql = "delete from interface_tree where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    public int updateTree(FuncTcTree tree){
        String sql = "update interface_tree set label = ? where id = ?";
        return jdbcTemplate.update(sql,tree.getLabel(),tree.getId());
    }

    public List<FuncTcInfo> nodeClick(int id){
        String sql = "select m.* from interface_tc_info  as m," +
                "(SELECT "+id +" as id UNION ALL select id from (select t1.id,if(find_in_set(pid, @pids) > 0," +
                " @pids := concat(@pids, ',', id), 0) as ischild from ( select id,pid from interface_tree t) t1," +
                "(select @pids := "+id+") t2) t3 where ischild != \"0\") as n where m.category = n.id";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper(FuncTcInfo.class));
    }
}

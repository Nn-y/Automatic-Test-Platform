package com.testplatform.platformbackend.service.IfTc;

import com.testplatform.platformbackend.dao.FuncTc.FuncTcTreeDao;
import com.testplatform.platformbackend.dao.IfTc.IfTcTreeDao;
import com.testplatform.platformbackend.entity.FuncTcInfo;
import com.testplatform.platformbackend.entity.FuncTcTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IfTcTreeServiceImpl implements IfTcTreeService{
    @Autowired
    IfTcTreeDao ifTcTreeDao;
    @Override
    public List<FuncTcTree> getTree(String project) {
        return ifTcTreeDao.getTree(project);
    }

    @Override
    public int addTree(int pid,String project) {
        return ifTcTreeDao.addTree(pid,project);
    }

    @Override
    public int deleteTree(int id) {
        return ifTcTreeDao.deleteTree(id);
    }

    @Override
    public int updateTree(FuncTcTree tree) {
        return ifTcTreeDao.updateTree(tree);
    }

    @Override
    public List<FuncTcInfo> nodeClick(int id) {
        return ifTcTreeDao.nodeClick(id);
    }
}

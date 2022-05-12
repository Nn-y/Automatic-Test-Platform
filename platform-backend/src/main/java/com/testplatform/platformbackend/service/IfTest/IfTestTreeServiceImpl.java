package com.testplatform.platformbackend.service.IfTest;

import com.testplatform.platformbackend.dao.IfTc.IfTcTreeDao;
import com.testplatform.platformbackend.dao.IfTest.IfTestTreeDao;
import com.testplatform.platformbackend.entity.FuncTcInfo;
import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.entity.IfList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IfTestTreeServiceImpl implements IfTestTreeService{
    @Autowired
    IfTestTreeDao ifTestTreeDao;
    @Override
    public List<FuncTcTree> getTree(String project) {
        return ifTestTreeDao.getTree(project);
    }

    @Override
    public int addTree(int pid,String project) {
        return ifTestTreeDao.addTree(pid,project);
    }

    @Override
    public int deleteTree(int id) {
        return ifTestTreeDao.deleteTree(id);
    }

    @Override
    public int updateTree(FuncTcTree tree) {
        return ifTestTreeDao.updateTree(tree);
    }

    @Override
    public List<IfList> nodeClick(int id) {
        return ifTestTreeDao.nodeClick(id);
    }
}

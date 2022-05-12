package com.testplatform.platformbackend.service.FuncTc;

import com.testplatform.platformbackend.dao.FuncTc.FuncTcTreeDao;
import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.entity.FuncTcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncTcTreeServiceImpl implements FuncTcTreeService{
    @Autowired
    FuncTcTreeDao funcTcTreeDao;
    @Override
    public List<FuncTcTree> getTree(String project) {
        return funcTcTreeDao.getTree(project);
    }

    @Override
    public int addTree(int pid,String project) {
        return funcTcTreeDao.addTree(pid,project);
    }

    @Override
    public int deleteTree(int id) {
        return funcTcTreeDao.deleteTree(id);
    }

    @Override
    public int updateTree(FuncTcTree tree) {
        return funcTcTreeDao.updateTree(tree);
    }

    @Override
    public List<FuncTcInfo> nodeClick(int id) {
        return funcTcTreeDao.nodeClick(id);
    }
}

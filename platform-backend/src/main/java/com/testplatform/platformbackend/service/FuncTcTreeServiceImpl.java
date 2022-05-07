package com.testplatform.platformbackend.service;

import com.testplatform.platformbackend.dao.FuncTcTreeDao;
import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.entity.FunctionTcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncTcTreeServiceImpl implements FuncTcTreeService{
    @Autowired
    FuncTcTreeDao funcTcTreeDao;
    @Override
    public List<FuncTcTree> getTree() {
        return funcTcTreeDao.getTree();
    }

    @Override
    public int addTree(int pid) {
        return funcTcTreeDao.addTree(pid);
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
    public List<FunctionTcInfo> nodeClick(int id) {
        return funcTcTreeDao.nodeClick(id);
    }
}

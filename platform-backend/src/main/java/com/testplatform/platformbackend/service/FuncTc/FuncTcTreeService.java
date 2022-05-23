package com.testplatform.platformbackend.service.FuncTc;

import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.entity.FuncTcInfo;

import java.util.List;

public interface FuncTcTreeService {
    List<FuncTcTree> getTree(int projectId);
    int addTree(int pid,int projectId);
    int deleteTree(int id);
    int updateTree(FuncTcTree tree);
    List<FuncTcInfo> nodeClick(int id);
}

package com.testplatform.platformbackend.service.IfTc;

import com.testplatform.platformbackend.entity.FuncTcInfo;
import com.testplatform.platformbackend.entity.FuncTcTree;

import java.util.List;

public interface IfTcTreeService {
    List<FuncTcTree> getTree(int projectId);
    int addTree(int pid,int projectId);
    int deleteTree(int id);
    int updateTree(FuncTcTree tree);
    List<FuncTcInfo> nodeClick(int id);
}

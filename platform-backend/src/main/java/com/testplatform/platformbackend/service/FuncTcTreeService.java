package com.testplatform.platformbackend.service;

import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.entity.FunctionTcInfo;

import java.util.List;

public interface FuncTcTreeService {
    List<FuncTcTree> getTree();
    int addTree(int pid);
    int deleteTree(int id);
    int updateTree(FuncTcTree tree);
    List<FunctionTcInfo> nodeClick(int id);
}

package com.testplatform.platformbackend.service.IfTest;

import com.testplatform.platformbackend.entity.FuncTcInfo;
import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.entity.IfList;

import java.util.List;

public interface IfTestTreeService {
    List<FuncTcTree> getTree(String project);
    int addTree(int pid,String project);
    int deleteTree(int id);
    int updateTree(FuncTcTree tree);
    List<IfList> nodeClick(int id);
}

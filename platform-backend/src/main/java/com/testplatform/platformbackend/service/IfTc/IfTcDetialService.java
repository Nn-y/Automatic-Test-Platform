package com.testplatform.platformbackend.service.IfTc;

import com.testplatform.platformbackend.entity.FuncTcDetial;

import java.util.List;

public interface IfTcDetialService {
    List<FuncTcDetial> getDetial(int id);
    String getPre(int id);
    int addRow(int id);
    int deleteRow(int xid);
    void updateDetial(List<FuncTcDetial> detials);
}

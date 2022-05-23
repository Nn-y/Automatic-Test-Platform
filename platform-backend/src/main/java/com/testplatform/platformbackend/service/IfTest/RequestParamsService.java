package com.testplatform.platformbackend.service.IfTest;

import com.testplatform.platformbackend.entity.RequestParams;

import java.util.List;

public interface RequestParamsService {
    List<RequestParams> getParams(int id);
    void updateParams(RequestParams params);
    int addRow(int id);
    int deleteRow(int xid);
}

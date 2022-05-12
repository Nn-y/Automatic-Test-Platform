package com.testplatform.platformbackend.service.IfTest;

import com.testplatform.platformbackend.entity.IfListDetial;

public interface IfListService {
    void addDefault(int nodeId);
    int deleteInfo(int id);
    int updateInfo(IfListDetial detial);
}

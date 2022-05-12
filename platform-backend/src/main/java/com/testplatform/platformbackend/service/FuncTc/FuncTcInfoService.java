package com.testplatform.platformbackend.service.FuncTc;

import com.testplatform.platformbackend.entity.FuncTcInfo;

import java.util.List;

public interface FuncTcInfoService {

    List<FuncTcInfo> getInfo();

    FuncTcInfo getOneInfo(int id);

    void addDefault(int nodeId);
//    name	version	tag	module	bugs	user	result	utime	ctime	level	status
    int updateInfo(FuncTcInfo info);

    int deleteInfo(int id);
}

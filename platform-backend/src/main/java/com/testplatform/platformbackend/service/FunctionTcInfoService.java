package com.testplatform.platformbackend.service;

import com.testplatform.platformbackend.entity.FunctionTcInfo;

import java.util.List;

public interface FunctionTcInfoService {

    List<FunctionTcInfo> getInfo();

    FunctionTcInfo getOneInfo(int id);

    void addDefault(int nodeId);
//    name	version	tag	module	bugs	user	result	utime	ctime	level	status
    int updateInfo(FunctionTcInfo info);

    int deleteInfo(int id);
}

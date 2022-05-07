package com.testplatform.platformbackend.service;

import com.testplatform.platformbackend.dao.FunctionTcInfoDao;
import com.testplatform.platformbackend.entity.FunctionTcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionTcInfoServiceImpl implements FunctionTcInfoService{
    @Autowired
    FunctionTcInfoDao functionTcInfoDao;
    @Override
    public List<FunctionTcInfo> getInfo(){
        return functionTcInfoDao.getInfo();
    }

    @Override
    public FunctionTcInfo getOneInfo(int id) {
        return functionTcInfoDao.getOneInfo(id);
    }

    @Override
    public void addDefault(int nodeId){
        functionTcInfoDao.addDefault(nodeId);
    }

    @Override
    public int updateInfo(FunctionTcInfo info) {
        return functionTcInfoDao.updateInfo(info);
    }

    @Override
    public int deleteInfo(int id) {
        return functionTcInfoDao.deleteInfo(id);
    }
}

package com.testplatform.platformbackend.service.IfTc;

import com.testplatform.platformbackend.dao.IfTc.IfTcInfoDao;
import com.testplatform.platformbackend.entity.FuncTcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IfTcInfoServiceImpl implements IfTcInfoService {
    @Autowired
    IfTcInfoDao ifTcInfoDao;
    @Override
    public List<FuncTcInfo> getInfo(){
        return ifTcInfoDao.getInfo();
    }

    @Override
    public FuncTcInfo getOneInfo(int id) {
        return ifTcInfoDao.getOneInfo(id);
    }

    @Override
    public void addDefault(int nodeId){
        ifTcInfoDao.addDefault(nodeId);
    }

    @Override
    public int updateInfo(FuncTcInfo info) {
        return ifTcInfoDao.updateInfo(info);
    }

    @Override
    public int deleteInfo(int id) {
        return ifTcInfoDao.deleteInfo(id);
    }
}

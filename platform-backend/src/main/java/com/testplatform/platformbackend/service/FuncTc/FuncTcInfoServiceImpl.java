package com.testplatform.platformbackend.service.FuncTc;

import com.testplatform.platformbackend.dao.FuncTc.FuncTcInfoDao;
import com.testplatform.platformbackend.entity.FuncTcInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncTcInfoServiceImpl implements FuncTcInfoService {
    @Autowired
    FuncTcInfoDao funcTcInfoDao;
    @Override
    public List<FuncTcInfo> getInfo(){
        return funcTcInfoDao.getInfo();
    }

    @Override
    public FuncTcInfo getOneInfo(int id) {
        return funcTcInfoDao.getOneInfo(id);
    }

    @Override
    public void addDefault(int nodeId){
        funcTcInfoDao.addDefault(nodeId);
    }

    @Override
    public int updateInfo(FuncTcInfo info) {
        return funcTcInfoDao.updateInfo(info);
    }

    @Override
    public int deleteInfo(int id) {
        return funcTcInfoDao.deleteInfo(id);
    }
}

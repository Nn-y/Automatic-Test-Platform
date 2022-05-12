package com.testplatform.platformbackend.service.IfTest;

import com.testplatform.platformbackend.dao.IfTest.IfListDao;
import com.testplatform.platformbackend.entity.IfListDetial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IfListServiceImpl implements IfListService {
    @Autowired
    IfListDao ifListDao;
    @Override
    public void addDefault(int nodeId) {
        ifListDao.addDefault(nodeId);
    }

    @Override
    public int deleteInfo(int id) {
        return ifListDao.deleteInfo(id);
    }

    @Override
    public int updateInfo(IfListDetial detial) {
        return ifListDao.updateInfo(detial);
    }
}

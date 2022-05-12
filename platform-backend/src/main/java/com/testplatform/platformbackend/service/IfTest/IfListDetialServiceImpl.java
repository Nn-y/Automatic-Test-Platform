package com.testplatform.platformbackend.service.IfTest;

import com.testplatform.platformbackend.dao.IfTest.IfListDetialDao;
import com.testplatform.platformbackend.entity.IfListDetial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IfListDetialServiceImpl implements IfListDetialService{
    @Autowired
    IfListDetialDao ifListDetialDao;
    @Override
    public IfListDetial getDetial(int id) {
        return ifListDetialDao.getDetial(id);
    }

    @Override
    public int save(IfListDetial detial) {
        return ifListDetialDao.save(detial);
    }
}

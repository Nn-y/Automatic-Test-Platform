package com.testplatform.platformbackend.service.IfTest;

import com.testplatform.platformbackend.dao.IfTest.JmeterDao;
import com.testplatform.platformbackend.entity.MyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JmeterServiceImpl implements JmeterService {
    @Autowired
    JmeterDao jmeterDao;
    @Override
    public MyRequest getRequest(int id) {
        return jmeterDao.getRequest(id);
    }
}

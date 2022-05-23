package com.testplatform.platformbackend.service.IfTest;

import com.testplatform.platformbackend.dao.IfTest.RequestParamsDao;
import com.testplatform.platformbackend.entity.RequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestParamsServiceImpl implements RequestParamsService{
    @Autowired
    RequestParamsDao requestParamsDao;
    @Override
    public List<RequestParams> getParams(int id) {
        return requestParamsDao.getParams(id);
    }

    @Override
    public void updateParams(RequestParams params) {
        requestParamsDao.updateParams(params);
    }

    @Override
    public int addRow(int id) {
        return requestParamsDao.addRow(id);
    }

    @Override
    public int deleteRow(int xid) {
        return requestParamsDao.deleteRow(xid);
    }
}

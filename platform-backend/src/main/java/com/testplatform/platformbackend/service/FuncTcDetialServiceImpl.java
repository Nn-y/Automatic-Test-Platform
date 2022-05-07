package com.testplatform.platformbackend.service;

import com.testplatform.platformbackend.dao.FuncTcDetialDao;
import com.testplatform.platformbackend.entity.FuncTcDetial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncTcDetialServiceImpl implements FuncTcDetialService {
    @Autowired
    FuncTcDetialDao funcTcDetialDao;
    @Override
    public List<FuncTcDetial> getDetial(int id) {
        return funcTcDetialDao.getDetial(id);
    }

    @Override
    public String getPre(int id) {
        return funcTcDetialDao.getPre(id);
    }

    @Override
    public int addRow(int id) {
        return funcTcDetialDao.addRow(id);
    }

    @Override
    public int deleteRow(int xid) {
        return funcTcDetialDao.deleteRow(xid);
    }

    @Override
    public void updateDetial(List<FuncTcDetial> detials) {
        funcTcDetialDao.updateDetial(detials);
    }


}

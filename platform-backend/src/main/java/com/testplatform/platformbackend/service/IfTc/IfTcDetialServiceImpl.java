package com.testplatform.platformbackend.service.IfTc;

import com.testplatform.platformbackend.dao.FuncTc.FuncTcDetialDao;
import com.testplatform.platformbackend.dao.IfTc.IfTcDetialDao;
import com.testplatform.platformbackend.entity.FuncTcDetial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IfTcDetialServiceImpl implements IfTcDetialService{

    @Autowired
    IfTcDetialDao ifTcDetialDao;
    @Override
    public List<FuncTcDetial> getDetial(int id) {
        return ifTcDetialDao.getDetial(id);
    }

    @Override
    public String getPre(int id) {
        return ifTcDetialDao.getPre(id);
    }

    @Override
    public int addRow(int id) {
        return ifTcDetialDao.addRow(id);
    }

    @Override
    public int deleteRow(int xid) {
        return ifTcDetialDao.deleteRow(xid);
    }

    @Override
    public void updateDetial(List<FuncTcDetial> detials) {
        ifTcDetialDao.updateDetial(detials);
    }
}

package com.testplatform.platformbackend.controller.FuncTc;

import com.testplatform.platformbackend.entity.FuncTcDetial;
import com.testplatform.platformbackend.service.FuncTc.FuncTcDetialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FuncTcDetialController {
    @Autowired
    FuncTcDetialService service;

    @ResponseBody
    @RequestMapping("/funcdetial")
    public List<FuncTcDetial> getDetial(@RequestParam("id") int id){
//        System.out.println(id);
        return service.getDetial(id);
    }

    @ResponseBody
    @RequestMapping("/funcpre")
    public String getPre(@RequestParam("id") int id){
        return service.getPre(id);
    }

    @ResponseBody
    @RequestMapping("/funcaddrow")
    public List<FuncTcDetial> addRow(@RequestParam("id") int id){
        service.addRow(id);
        return service.getDetial(id);
    }

    @ResponseBody
    @PostMapping("/funcdeleterow")
    public List<FuncTcDetial> deleteRow(@RequestBody FuncTcDetial funcTcDetial){
        int xid = funcTcDetial.getXid();
        int id = funcTcDetial.getId();
        service.deleteRow(xid);
        return service.getDetial(id);
    }

    @ResponseBody
    @PostMapping("/funcdetialupdate")
    public void detialUpdate(@RequestBody List<FuncTcDetial> detials){
        service.updateDetial(detials);
    }
}

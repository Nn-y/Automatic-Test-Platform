package com.testplatform.platformbackend.controller.IfTc;

import com.testplatform.platformbackend.entity.FuncTcDetial;
import com.testplatform.platformbackend.service.FuncTc.FuncTcDetialService;
import com.testplatform.platformbackend.service.IfTc.IfTcDetialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IfTcDetialController {
    @Autowired
    IfTcDetialService service;

    @ResponseBody
    @RequestMapping("/ifdetial")
    @CrossOrigin
    public List<FuncTcDetial> getDetial(@RequestParam("id") int id){
//        System.out.println(id);
        return service.getDetial(id);
    }

    @ResponseBody
    @RequestMapping("/ifpre")
    @CrossOrigin
    public String getPre(@RequestParam("id") int id){
        return service.getPre(id);
    }

    @ResponseBody
    @RequestMapping("/ifaddrow")
    @CrossOrigin
    public List<FuncTcDetial> addRow(@RequestParam("id") int id){
        service.addRow(id);
        return service.getDetial(id);
    }

    @ResponseBody
    @PostMapping("/ifdeleterow")
    @CrossOrigin
    public List<FuncTcDetial> deleteRow(@RequestBody FuncTcDetial funcTcDetial){
        int xid = funcTcDetial.getXid();
        int id = funcTcDetial.getId();
        service.deleteRow(xid);
        return service.getDetial(id);
    }

    @ResponseBody
    @PostMapping("/ifdetialupdate")
    @CrossOrigin
    public void detialUpdate(@RequestBody List<FuncTcDetial> detials){
        service.updateDetial(detials);
    }
}

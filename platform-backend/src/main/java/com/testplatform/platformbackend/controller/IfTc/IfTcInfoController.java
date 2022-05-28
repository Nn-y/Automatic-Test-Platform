package com.testplatform.platformbackend.controller.IfTc;

import com.testplatform.platformbackend.entity.FuncTcInfo;
import com.testplatform.platformbackend.service.FuncTc.FuncTcInfoService;
import com.testplatform.platformbackend.service.FuncTc.FuncTcTreeService;
import com.testplatform.platformbackend.service.IfTc.IfTcInfoService;
import com.testplatform.platformbackend.service.IfTc.IfTcTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class IfTcInfoController {
    @Autowired
    IfTcInfoService service;

    @Autowired
    IfTcTreeService ifTcTreeService;

    @ResponseBody
    @RequestMapping("/interfaceInfo")
    @CrossOrigin
    public List<FuncTcInfo> getAllInfo() {
        return service.getInfo();
    }

    @ResponseBody
    @RequestMapping("/interfaceInfo/one")
    @CrossOrigin
    public FuncTcInfo getOneInfo(@RequestParam("id") int id){
        return service.getOneInfo(id);
    }

    @ResponseBody
    @RequestMapping("/ifInfoAddDefault")
    @CrossOrigin
    public List<FuncTcInfo> addDefault(@RequestParam("nodeId") int nodeId){
        service.addDefault(nodeId);
        return ifTcTreeService.nodeClick(nodeId);
    }

    @ResponseBody
    @PostMapping("/ifInfoUpdate")
    @CrossOrigin
    //    name	version	tag	module	bugs	user	result	utime	ctime	level	status
    public int updateInfo(@RequestBody FuncTcInfo info){
        return service.updateInfo(info);
    }

    @ResponseBody
    @RequestMapping("/ifInfoDelete")
    @CrossOrigin
    public List<FuncTcInfo> deleteInfo(@RequestParam Map<String,String> map){
        int id = Integer.parseInt(map.get("id"));
        int nodeId = Integer.parseInt(map.get("nodeId"));
        service.deleteInfo(id);
        return ifTcTreeService.nodeClick(nodeId);
    }
}

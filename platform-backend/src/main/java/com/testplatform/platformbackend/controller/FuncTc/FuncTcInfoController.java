package com.testplatform.platformbackend.controller.FuncTc;

import com.testplatform.platformbackend.entity.FuncTcInfo;
import com.testplatform.platformbackend.service.FuncTc.FuncTcTreeService;
import com.testplatform.platformbackend.service.FuncTc.FuncTcInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class FuncTcInfoController {
    @Autowired
    FuncTcInfoService service;

    @Autowired
    FuncTcTreeService funcTcTreeService;

    @ResponseBody
    @RequestMapping("/functionInfo")
    @CrossOrigin
    public List<FuncTcInfo> getAllInfo() {
        return service.getInfo();
    }

    @ResponseBody
    @RequestMapping("/functionInfo/one")
    @CrossOrigin
    public FuncTcInfo getOneInfo(@RequestParam("id") int id){
        return service.getOneInfo(id);
    }

    @ResponseBody
    @RequestMapping("/funcInfoAddDefault")
    @CrossOrigin
    public List<FuncTcInfo> addDefault(@RequestParam("nodeId") int nodeId){
        service.addDefault(nodeId);
        return funcTcTreeService.nodeClick(nodeId);
    }

    @ResponseBody
    @PostMapping("/funcInfoUpdate")
    @CrossOrigin
    //    name	version	tag	module	bugs	user	result	utime	ctime	level	status
    public int updateInfo(@RequestBody FuncTcInfo info){
        return service.updateInfo(info);
    }

    @ResponseBody
    @RequestMapping("/funcInfoDelete")
    @CrossOrigin
    public List<FuncTcInfo> deleteInfo(@RequestParam Map<String,String> map){
        int id = Integer.parseInt(map.get("id"));
        int nodeId = Integer.parseInt(map.get("nodeId"));
        service.deleteInfo(id);
        return funcTcTreeService.nodeClick(nodeId);
    }



}

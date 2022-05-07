package com.testplatform.platformbackend.controller;

import com.testplatform.platformbackend.entity.FunctionTcInfo;
import com.testplatform.platformbackend.service.FuncTcTreeService;
import com.testplatform.platformbackend.service.FunctionTcInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FunctionTcInfoController {
    @Autowired
    FunctionTcInfoService service;

    @Autowired
    FuncTcTreeService funcTcTreeService;

    @ResponseBody
    @RequestMapping("/functionInfo")
    public List<FunctionTcInfo> getAllInfo() {
        return service.getInfo();
    }

    @ResponseBody
    @RequestMapping("/functionInfo/one")
    public FunctionTcInfo getOneInfo(@RequestParam("id") int id){
        return service.getOneInfo(id);
    }

    @ResponseBody
    @RequestMapping("/funcInfoAddDefault")
    public List<FunctionTcInfo> addDefault(@RequestParam("nodeId") int nodeId){
        service.addDefault(nodeId);
        return funcTcTreeService.nodeClick(nodeId);
    }

    @ResponseBody
    @PostMapping("/funcInfoUpdate")
    //    name	version	tag	module	bugs	user	result	utime	ctime	level	status
    public int updateInfo(@RequestBody FunctionTcInfo info){
        return service.updateInfo(info);
    }

    @ResponseBody
    @PostMapping("/funcInfoDelete")
    public int deleteInfo(@RequestBody FunctionTcInfo info){
        int id = info.getId();
//        System.out.println(id);
        return service.deleteInfo(id);
    }



}

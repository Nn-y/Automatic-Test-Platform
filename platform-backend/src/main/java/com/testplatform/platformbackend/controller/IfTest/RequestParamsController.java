package com.testplatform.platformbackend.controller.IfTest;

import com.testplatform.platformbackend.entity.RequestParams;
import com.testplatform.platformbackend.service.IfTest.RequestParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RequestParamsController {
    @Autowired
    RequestParamsService service;

    @ResponseBody
    @RequestMapping("/getparams")
    public List<RequestParams> getParams(@RequestParam("id") int id){
        return service.getParams(id);
    }
    @ResponseBody
    @PostMapping("/test")
    public List<RequestParams> test(@RequestParam("id") int id){
        return service.getParams(id);
    }

    @ResponseBody
    @PostMapping("/updateparams")
    public void updateParams(@RequestBody RequestParams params){
        service.updateParams(params);
    }

    @ResponseBody
    @RequestMapping("/addparams")
    public List<RequestParams> addRow(@RequestParam("id") int id){
        service.addRow(id);
        return service.getParams(id);
    }

    @ResponseBody
    @PostMapping("/deleteparams")
    public List<RequestParams> deleteRow(@RequestBody RequestParams param){
        int id = param.getId();
        int xid = param.getXid();
        service.deleteRow(xid);
        return service.getParams(id);
    }
}

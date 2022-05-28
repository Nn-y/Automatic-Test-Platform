package com.testplatform.platformbackend.controller.IfTest;

import com.testplatform.platformbackend.entity.FuncTcInfo;
import com.testplatform.platformbackend.entity.IfList;
import com.testplatform.platformbackend.service.IfTest.IfListService;
import com.testplatform.platformbackend.service.IfTest.IfTestTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class IfListController {
    @Autowired
    IfListService service;

    @Autowired
    IfTestTreeService ifTestTreeService;

    @ResponseBody
    @RequestMapping("/ifListAddDefault")
    @CrossOrigin
    public List<IfList> addDefault(@RequestParam("nodeId") int nodeId){
        service.addDefault(nodeId);
        return ifTestTreeService.nodeClick(nodeId);
    }

    @ResponseBody
    @RequestMapping("/ifListDelete")
    @CrossOrigin
    public List<IfList> deleteInfo(@RequestParam Map<String,String> map){
        int id = Integer.parseInt(map.get("id"));
        int nodeId = Integer.parseInt(map.get("nodeId"));
        service.deleteInfo(id);
        return ifTestTreeService.nodeClick(nodeId);
    }
}

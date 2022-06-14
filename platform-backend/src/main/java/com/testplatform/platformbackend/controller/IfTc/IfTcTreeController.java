package com.testplatform.platformbackend.controller.IfTc;

import com.testplatform.platformbackend.entity.FuncTcInfo;
import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.service.FuncTc.FuncTcTreeService;
import com.testplatform.platformbackend.service.IfTc.IfTcTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class IfTcTreeController {
    @Autowired
    IfTcTreeService service;

    @ResponseBody
    @RequestMapping("/iftctree")
    @CrossOrigin
    public List<FuncTcTree> getTree(@RequestParam("projectId") int projectId){
//        System.out.println(project);
        return service.getTree(projectId);
    }

    @ResponseBody
    @RequestMapping("/iftctree/add")
    @CrossOrigin
    public List<FuncTcTree> addTree(@RequestParam Map<String,String> map){
        service.addTree(Integer.parseInt(map.get("id")),Integer.parseInt(map.get("projectId")));
        return service.getTree(Integer.parseInt(map.get("projectId")));
    }

    @ResponseBody
    @RequestMapping("/iftctree/del")
    @CrossOrigin
    public int deleteTree(@RequestParam("id") int id){
        return service.deleteTree(id);
    }

    @ResponseBody
    @PostMapping("/iftctree/update")
    @CrossOrigin
    public List<FuncTcTree> updateTree(@RequestBody FuncTcTree tree){
        service.updateTree(tree);
        return service.getTree(tree.getProject());
    }

    @ResponseBody
    @RequestMapping("/iftctree/nodeclick")
    @CrossOrigin
    public List<FuncTcInfo> nodeClick(@RequestParam("id") int id){
        return service.nodeClick(id);
    }
}

package com.testplatform.platformbackend.controller.FuncTc;

import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.entity.FuncTcInfo;
import com.testplatform.platformbackend.service.FuncTc.FuncTcTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class FuncTcTreeController {
    @Autowired
    FuncTcTreeService service;

    @ResponseBody
    @RequestMapping("/functctree")
    public List<FuncTcTree> getTree(@RequestParam("projectId") int projectId){
//        System.out.println(project);
        return service.getTree(projectId);
    }

    @ResponseBody
    @RequestMapping("/functctree/add")
    public List<FuncTcTree> addTree(@RequestParam Map<String,String> map){
        service.addTree(Integer.parseInt(map.get("id")),Integer.parseInt(map.get("projectId")));
        return service.getTree(Integer.parseInt(map.get("projectId")));
    }

    @ResponseBody
    @RequestMapping("/functctree/del")
    public List<FuncTcTree> deleteTree(@RequestParam Map<String,String> map){
        service.deleteTree(Integer.parseInt(map.get("id")));
        return service.getTree(Integer.parseInt(map.get("projectId")));
    }

    @ResponseBody
    @PostMapping("/functctree/update")
    public List<FuncTcTree> updateTree(@RequestBody FuncTcTree tree){
        service.updateTree(tree);
        return service.getTree(tree.getProject());
    }

    @ResponseBody
    @RequestMapping("/functctree/nodeclick")
    public List<FuncTcInfo> nodeClick(@RequestParam("id") int id){
        return service.nodeClick(id);
    }
}

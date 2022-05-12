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
    public List<FuncTcTree> getTree(@RequestParam("name") String project){
//        System.out.println(project);
        return service.getTree(project);
    }

    @ResponseBody
    @RequestMapping("/iftctree/add")
    public List<FuncTcTree> addTree(@RequestParam Map<String,String> map){
        service.addTree(Integer.parseInt(map.get("id")),map.get("name"));
        return service.getTree(map.get("name"));
    }

    @ResponseBody
    @RequestMapping("/iftctree/del")
    public List<FuncTcTree> deleteTree(@RequestParam Map<String,String> map){
        service.deleteTree(Integer.parseInt(map.get("id")));
        return service.getTree(map.get("name"));
    }

    @ResponseBody
    @PostMapping("/iftctree/update")
    public List<FuncTcTree> updateTree(@RequestBody FuncTcTree tree){
        service.updateTree(tree);
        return service.getTree(tree.getProject());
    }

    @ResponseBody
    @RequestMapping("/iftctree/nodeclick")
    public List<FuncTcInfo> nodeClick(@RequestParam("id") int id){
        return service.nodeClick(id);
    }
}

package com.testplatform.platformbackend.controller.IfTest;

import com.testplatform.platformbackend.entity.FuncTcInfo;
import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.entity.IfList;
import com.testplatform.platformbackend.service.IfTc.IfTcTreeService;
import com.testplatform.platformbackend.service.IfTest.IfTestTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class IfTestTreeController {
    @Autowired
    IfTestTreeService service;

    @ResponseBody
    @RequestMapping("/iftesttree")
    @CrossOrigin
    public List<FuncTcTree> getTree(@RequestParam("projectId") int projectId){
//        System.out.println(project);
        return service.getTree(projectId);
    }

    @ResponseBody
    @RequestMapping("/iftesttree/add")
    @CrossOrigin
    public List<FuncTcTree> addTree(@RequestParam Map<String,String> map){
        service.addTree(Integer.parseInt(map.get("id")),Integer.parseInt(map.get("projectId")));
        return service.getTree(Integer.parseInt(map.get("projectId")));
    }

    @ResponseBody
    @RequestMapping("/iftesttree/del")
    @CrossOrigin
    public List<FuncTcTree> deleteTree(@RequestParam Map<String,String> map){
        service.deleteTree(Integer.parseInt(map.get("id")));
        return service.getTree(Integer.parseInt(map.get("projectId")));
    }

    @ResponseBody
    @PostMapping("/iftesttree/update")
    @CrossOrigin
    public List<FuncTcTree> updateTree(@RequestBody FuncTcTree tree){
        service.updateTree(tree);
        return service.getTree(tree.getProject());
    }

    @ResponseBody
    @RequestMapping("/iftesttree/nodeclick")
    @CrossOrigin
    public List<IfList> nodeClick(@RequestParam("id") int id){
        return service.nodeClick(id);
    }
}

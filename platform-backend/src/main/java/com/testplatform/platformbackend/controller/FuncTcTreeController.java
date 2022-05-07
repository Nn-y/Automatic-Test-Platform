package com.testplatform.platformbackend.controller;

import com.testplatform.platformbackend.entity.FuncTcTree;
import com.testplatform.platformbackend.entity.FunctionTcInfo;
import com.testplatform.platformbackend.service.FuncTcTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FuncTcTreeController {
    @Autowired
    FuncTcTreeService service;

    @ResponseBody
    @RequestMapping("/functctree")
    public List<FuncTcTree> getTree(){
        return service.getTree();
    }

    @ResponseBody
    @RequestMapping("/functctree/add")
    public List<FuncTcTree> addTree(@RequestParam("pid") int pid){
        service.addTree(pid);
        return service.getTree();
    }

    @ResponseBody
    @RequestMapping("/functctree/del")
    public List<FuncTcTree> deleteTree(@RequestParam("id") int id){
        service.deleteTree(id);
        return service.getTree();
    }

    @ResponseBody
    @PostMapping("/functctree/update")
    public List<FuncTcTree> updateTree(@RequestBody FuncTcTree tree){
        service.updateTree(tree);
        return service.getTree();
    }

    @ResponseBody
    @RequestMapping("/functctree/nodeclick")
    public List<FunctionTcInfo> nodeClick(@RequestParam("id") int id){
        return service.nodeClick(id);
    }
}

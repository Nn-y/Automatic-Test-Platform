package com.testplatform.platformbackend.controller;

import com.testplatform.platformbackend.entity.Project;
import com.testplatform.platformbackend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {
    @Autowired
    ProjectService service;

    @ResponseBody
    @RequestMapping("/getprojects")
    @CrossOrigin
    public List<Project> getProjects(@RequestParam("user") String user){
        return service.getProjects(user);
    }

    @ResponseBody
    @RequestMapping("/getproject")
    @CrossOrigin
    public String getProject(@RequestParam int projectId){
        return service.getProject(projectId);
    }

    @ResponseBody
    @RequestMapping("/addproject")
    @CrossOrigin
    public List<Project> addProject(@RequestParam("user") String user){
        service.addProject(user);
        return service.getProjects(user);
    }

    @ResponseBody
    @RequestMapping("/delproject")
    @CrossOrigin
    public List<Project> delProject(@RequestParam Map<String,String> map){
//        System.out.println(map.get("id"));
        service.delProject(Integer.parseInt(map.get("id")));
        return service.getProjects(map.get("user"));
    }

    @ResponseBody
    @PostMapping("/updateproject")
    @CrossOrigin
    public void updateProject(@RequestBody List<Project> projects){
        service.updateProject(projects);
    }
}

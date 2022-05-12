package com.testplatform.platformbackend.controller;

import com.testplatform.platformbackend.entity.Project;
import com.testplatform.platformbackend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    ProjectService service;

    @ResponseBody
    @RequestMapping("/getprojects")
    public List<Project> getProjects(){
        return service.getProjects();
    }
}

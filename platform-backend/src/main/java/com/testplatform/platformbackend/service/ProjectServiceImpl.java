package com.testplatform.platformbackend.service;

import com.testplatform.platformbackend.dao.ProjectDao;
import com.testplatform.platformbackend.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    ProjectDao projectDao;
    @Override
    public List<Project> getProjects() {
        return projectDao.getProjects();
    }
}

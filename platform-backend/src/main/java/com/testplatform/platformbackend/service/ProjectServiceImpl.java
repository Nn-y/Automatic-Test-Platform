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
    public List<Project> getProjects(String user) {
        return projectDao.getProjects(user);
    }

    @Override
    public String getProject(int projectId) {
        return projectDao.getProject(projectId);
    }

    @Override
    public int addProject(String user) {
        return projectDao.addProject(user);
    }

    @Override
    public int delProject(int id) {
        return projectDao.delProject(id);
    }

    @Override
    public void updateProject(List<Project> projects) {
        projectDao.updateProject(projects);
    }
}

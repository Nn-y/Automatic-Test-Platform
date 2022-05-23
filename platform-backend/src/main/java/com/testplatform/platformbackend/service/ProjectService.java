package com.testplatform.platformbackend.service;

import com.testplatform.platformbackend.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getProjects(String user);
    String getProject(int projectId);
    int addProject(String user);
    int delProject(int id);
    void updateProject(List<Project> projects);
}

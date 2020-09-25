package com.example.demo.service;

import java.util.List;

import com.example.demo.controller.request.ProjectRequest;
import com.example.demo.repository.model.Project;

public interface ProjectService {

	Project saveProject(ProjectRequest projectRequest);
	
	Project getProject(long projectId);
	
	List<Project> getProjects();
}

package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.request.ProjectRequest;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.model.Project;
import com.example.demo.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public Project saveProject(ProjectRequest projectRequest) {
		Project project = new Project();
		project.setProjectName(projectRequest.getProjectName());
		project.setProjectCost(projectRequest.getProjectCost());
		return projectRepository.save(project);
	}

	@Override
	public Project getProject(long projectId) {
		Optional<Project> optional = projectRepository.findById(projectId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return new Project();
	}

	@Override
	public List<Project> getProjects() {
		return projectRepository.findAll();
	}
	
	

	
}

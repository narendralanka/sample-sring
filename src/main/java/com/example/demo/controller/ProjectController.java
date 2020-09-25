package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.ProjectConstants;
import com.example.demo.controller.request.ProjectRequest;
import com.example.demo.repository.model.Project;
import com.example.demo.service.impl.ProjectServiceImpl;

@RestController
@RequestMapping(path=ProjectConstants.BASE_PATH)
public class ProjectController {
	
	@Autowired
	ProjectServiceImpl projectService;
	
	@PostMapping(path="/projects")
	public ResponseEntity<Project> createProject(@RequestBody ProjectRequest projectRequest) {
		return new ResponseEntity<>(projectService.saveProject(projectRequest), HttpStatus.CREATED);
	}

	@GetMapping(path="/projects/{projectId}")
	public ResponseEntity<Project> getProject(@PathVariable long projectId) {
		return new ResponseEntity<>(projectService.getProject(projectId), HttpStatus.OK);
	}
	
	@GetMapping(path="/projects")
	public ResponseEntity<List<Project>> getProjects() {
		return new ResponseEntity<>(projectService.getProjects(), HttpStatus.OK);
	}
}

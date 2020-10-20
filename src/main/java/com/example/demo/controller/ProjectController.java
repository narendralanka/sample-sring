package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static final Logger LOGGER = LogManager.getLogger(ProjectController.class.getName());
	
	@Autowired
	ProjectServiceImpl projectService;
	
	@PostMapping(path="/projects")
	public ResponseEntity<Project> createProject(@RequestBody ProjectRequest projectRequest) {
		LOGGER.info("Request for creation of project : " + projectRequest.toString());
		Project projectResponse = projectService.saveProject(projectRequest);
		LOGGER.info("Response for creation of project : " + projectResponse.toString());
		return new ResponseEntity<>(projectResponse, HttpStatus.CREATED);
	}

	@GetMapping(path="/projects/{projectId}")
	public ResponseEntity<Project> getProject(@PathVariable long projectId) {
		LOGGER.info("Request for retrieval of project : " + projectId);
		Project projectResponse = projectService.getProject(projectId);
		LOGGER.info("Response for retrieval of project : " + projectResponse.toString());
		return new ResponseEntity<>(projectResponse, HttpStatus.OK);
	}
	
	@GetMapping(path="/projects")
	public ResponseEntity<List<Project>> getProjects() {
		LOGGER.info("Request for getProjects");
		List<Project> projectResponses = projectService.getProjects();
		LOGGER.info("Response for gteProjects : " + projectResponses);
		return new ResponseEntity<>(projectResponses, HttpStatus.OK);
	}
}

package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Project;
import com.example.demo.service.ProjectService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@PostMapping("/addprojects")
	public ResponseEntity<Project> createProject(@RequestBody Project project) {
		projectService.addProject(project);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/projects")
	public ResponseEntity<List<Project>> getAllProjects(){
		return ResponseEntity.ok().body(projectService.getAllProjects());
	}
	
	@GetMapping("/getprojects/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable long projectId){
		return ResponseEntity.ok().body(projectService.getProjectById(projectId));
	}
	
	@DeleteMapping("/deleteprojects/{projectId}")
	public ResponseEntity<Project> deleteProjectById(@PathVariable long projectId) {
		projectService.deleteProject(projectId);
		projectService.deleteTaskOfProject(projectId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/updateprojects/{projectId}")
	public Project updateProject(@PathVariable int projectId,@RequestBody Project p) {
		return projectService.updateProject(projectId,p.getProjectName(),p.getProjectDescription());
	}
	
	
	

}

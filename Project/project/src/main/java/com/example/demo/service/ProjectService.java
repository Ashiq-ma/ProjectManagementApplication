package com.example.demo.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Project;
import com.example.demo.repository.ProjectRepository;




@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void addProject(Project project) {
		projectRepo.save(project);
	}
	
	public List<Project> getAllProjects() {
		return projectRepo.findAll();
	}
	
	public Project getProjectById(long projectId) {
		Optional<Project> pro=projectRepo.findById(projectId);
		Project p=pro.get();
		return p;
	}
	
	public Project updateProject(long projectId,String projectName,String projectDescription) {
		Optional<Project> pro=projectRepo.findById(projectId);
		Project p=pro.get();
		p.setProjectName(projectName);
		p.setProjectDescription(projectDescription);
		return projectRepo.save(p);
	}
	
	public void deleteProject(long projectId) {
		projectRepo.deleteById(projectId);
	}
	
	private String url="http://localhost:8084/admin/deleteByProject/{projectId}";
	
	public void deleteTaskOfProject(long projectId) {
		restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, projectId);
		}

}

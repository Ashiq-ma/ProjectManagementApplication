package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Task getTaskById(long taskId) {
		Task t= taskRepo.findById(taskId).orElseThrow(()->new NoSuchElementException("no id found"));
		return t;
	}
	
	public List<Task> getAllTasks(){
		return taskRepo.findAll();
	}
	
	public Task addTask(Task task) {
		return taskRepo.save(task);
	}
	
	public Task updateStatus(long taskId,String status) {
		Task t=taskRepo.findById(taskId).orElseThrow(()->new NoSuchElementException("no id found"));
		t.setStatus(status);
		return taskRepo.save(t);
	}
	
	public void deleteTask(long taskId) {
		taskRepo.deleteById(taskId);
	}

//	public Task addTask(Task task, long projectId) {
//		Task t=taskRepo.findById(projectId).orElseThrow(()->new NoSuchElementException("Project not found"));
//		t.setProjectId(projectId);
//		return taskRepo.save(task);
//	}
	
	public Task getTaskByUserId(long userId) {
		Task t=taskRepo.findByUserId(userId).orElseThrow(()->new NoSuchElementException("User not found"));
		return t;
	}
	
	public void deleteTaskByProjectId(long projectId) {
		List<Task> t=taskRepo.findByProjectId(projectId).orElseThrow(()->new NoSuchElementException("Task not found"));
		taskRepo.deleteAll(t);
		
	}
	
	public Task assignTaskToUser(long taskId,long userId) {
		Task t=taskRepo.findById(taskId).orElseThrow(()->new NoSuchElementException("no id found"));
		t.setUserId(userId);
		return taskRepo.save(t);
	}
	
	public Task updateTask(long taskId,long userId,String status,String taskName,String taskDescription,long projectId) {
		Task t=taskRepo.findById(taskId).orElseThrow(()->new NoSuchElementException("no id found"));
		t.setUserId(userId);
		t.setProjectId(projectId);
		t.setStatus(status);
		t.setTaskDescription(taskDescription);
		t.setTaskName(taskName);
		return taskRepo.save(t);
	}
	
private String url="http://localhost:8086/admin/deleteCommentByTask/{taskId}";
	
	public void deleteCommentOfTask(long taskId) {
		restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, taskId);
		}


}

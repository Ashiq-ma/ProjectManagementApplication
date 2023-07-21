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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Task;

import com.example.demo.service.TaskService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private TaskService taskService;
	
	
	
	@PostMapping("/addtask")
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		return ResponseEntity.ok().body(taskService.addTask(task));
		
	}
	
	@GetMapping("/gettasks")
	public ResponseEntity<List<Task>> getAllTask(){
		return ResponseEntity.ok().body(taskService.getAllTasks());
	}
	
	@GetMapping("gettask/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable long taskId) {
		return ResponseEntity.ok().body(taskService.getTaskById(taskId));
	}
	
	@PutMapping("/updatestatus/{taskId}")
	public ResponseEntity<Task> updateStatus(@PathVariable long taskId,@RequestBody Task task) {
		return ResponseEntity.ok().body(taskService.updateStatus(taskId,task.getStatus()));
	}
	
	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<Task> deleteTask(@PathVariable long taskId) {
		taskService.deleteTask(taskId);
		taskService.deleteCommentOfTask(taskId);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/deleteByProject/{projectId}")
	public ResponseEntity<Task> deleteTaskByProject(@PathVariable long projectId){
		taskService.deleteTaskByProjectId(projectId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/assignTask/{taskId}")
	public ResponseEntity<Task> assignTask(@RequestBody Task task ,@PathVariable long taskId){
		taskService.assignTaskToUser(taskId, task.getUserId());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/updatetasks/{taskId}")
	public ResponseEntity<Task> updateTask(@PathVariable long taskId,@RequestBody Task task) {
	return ResponseEntity.ok().body(taskService.updateTask(taskId,task.getUserId(),task.getStatus(),task.getStatus(),task.getTaskDescription(),task.getProjectId()));
	}

	
	
	

}
